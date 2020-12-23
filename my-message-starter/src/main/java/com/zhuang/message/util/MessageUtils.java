package com.zhuang.message.util;

import com.zhuang.message.MessageSender;
import com.zhuang.message.enums.MessageType;
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
    public void init() {
        _this = this;
    }

    public static boolean send(MessageType messageType, String templateId, Map<String, Object> params, String toUsers) {
        Optional<MessageSender> optionalMessageSender = _this.messageSenderList.stream().filter(c -> c.getMessageType() == messageType).findFirst();
        if (optionalMessageSender.isPresent()) {
            return optionalMessageSender.get().send(templateId, params, toUsers);
        } else {
            throw new RuntimeException("can not find the message sender!");
        }
    }

    public static boolean sendSms(String templateId, Map<String, Object> params, String toUsers) {
        return send(MessageType.SMS, templateId, params, toUsers);
    }
    
}
