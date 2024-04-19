package com.zhuang.message.util;

import com.zhuang.message.MessageSender;
import com.zhuang.message.config.MyMessageProperties;
import com.zhuang.message.constant.MsgParams;
import com.zhuang.message.enums.MessageType;
import com.zhuang.message.enums.MsgType;
import com.zhuang.message.model.SendResult;
import com.zhuang.message.sms.AliYunSmsSender;
import com.zhuang.message.sms.MasSmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MessageUtils {

    private static MessageUtils _this;
    @Autowired
    private List<MessageSender> messageSenderList;
    @Autowired
    private MyMessageProperties myMessageProperties;

    @PostConstruct
    private void init() {
        _this = this;
    }

    public static SendResult send(MessageType messageType, String templateId, Map<String, Object> params, String toUsers) {
        List<MessageSender> messageSenderList = _this.messageSenderList.stream().filter(c -> c.getMessageType() == messageType).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(messageSenderList)) {
            throw new RuntimeException("can not find the message sender!");
        }
        MessageSender messageSender;
        if (messageSenderList.size() == 1) {
            messageSender = messageSenderList.get(0);
        } else if (messageSenderList.stream().allMatch(c -> c.getMessageType().equals(MessageType.SMS))) {
            // Mas支持非模板的发送方式
            if (MyMessageProperties.SMS_TYPE_MAS.equals(_this.myMessageProperties.getDefaultSmsType()) || MyMessageProperties.NONE_TEMPLATE_TAG.equals(templateId)) {
                messageSender = messageSenderList.stream().filter(c -> c instanceof MasSmsSender).findFirst().orElse(null);
            } else {
                messageSender = messageSenderList.stream().filter(c -> c instanceof AliYunSmsSender).findFirst().orElse(null);
            }
        } else {
            throw new RuntimeException("found more one the message sender!");
        }
        return messageSender.send(templateId, params, toUsers);
    }

    public static SendResult sendSms(String templateId, Map<String, Object> params, String toUsers) {
        return send(MessageType.SMS, templateId, params, toUsers);
    }

    public static SendResult sendMsg(MsgType msgType, String templateId, Map<String, Object> params, String toUsers) {
        params.put(MsgParams.MSG_TYPE, msgType.getValue());
        return send(MessageType.MSG, templateId, params, toUsers);
    }
}
