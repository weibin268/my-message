package com.zhuang.message.util;

import com.zhuang.message.MessageSender;
import com.zhuang.message.constant.MsgParams;
import com.zhuang.message.enums.MessageType;
import com.zhuang.message.enums.MsgType;
import com.zhuang.message.model.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class MessageUtils {

    private static MessageUtils _this;
    @Autowired
    private List<MessageSender> messageSenderList;

    @PostConstruct
    private void init() {
        _this = this;
    }

    public static SendResult send(MessageType messageType, String templateId, Map<String, Object> params, String toUsers) {
        Optional<MessageSender> optionalMessageSender = _this.messageSenderList.stream().filter(c -> c.getMessageType() == messageType).findFirst();
        if (optionalMessageSender.isPresent()) {
            return optionalMessageSender.get().send(templateId, params, toUsers);
        } else {
            throw new RuntimeException("can not find the message sender!");
        }
    }

    public static SendResult sendSms(String templateId, Map<String, Object> params, String toUsers) {
        return send(MessageType.SMS, templateId, params, toUsers);
    }

    public static SendResult sendMsg(MsgType msgType, String templateId, Map<String, Object> params, String toUsers) {
        params.put(MsgParams.MSG_TYPE, msgType.getValue());
        return send(MessageType.MSG, templateId, params, toUsers);
    }
}
