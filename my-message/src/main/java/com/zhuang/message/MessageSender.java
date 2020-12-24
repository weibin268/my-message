package com.zhuang.message;

import com.zhuang.message.enums.MessageType;
import com.zhuang.message.model.SendResult;

import java.util.Map;

public interface MessageSender {

    SendResult send(String templateId, Map<String, Object> params, String toUsers);

    MessageType getMessageType();

}
