package com.zhuang.message;

import com.zhuang.message.enums.MessageType;

import java.util.Map;

public interface MessageSender {

    boolean send(String templateId, Map<String, Object> params, String toUsers);

    MessageType getMessageType();

}
