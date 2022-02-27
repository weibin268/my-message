package com.zhuang.message;

import com.zhuang.message.config.MyMessageProperties;
import com.zhuang.message.model.SendResult;
import com.zhuang.message.service.MessageLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public abstract class BaseMessageSender implements MessageSender {

    @Autowired
    private MyMessageProperties myMessageProperties;
    @Autowired
    private MessageLogService messageLogService;

    @Override
    public SendResult send(String templateId, Map<String, Object> params, String toUsers) {
        SendResult result = sendInternal(templateId, params, toUsers);
        if (myMessageProperties.isEnableLog()) {
            String tempToUser = toUsers;
            int maxToUserLength = 1000;
            if (tempToUser.length() > maxToUserLength) {
                tempToUser = tempToUser.substring(0, maxToUserLength);
            }
            messageLogService.add(getMessageType(), templateId, params, tempToUser, result.getContent(), result.isSuccess(), result.getMessage());
        }
        return result;
    }

    public abstract SendResult sendInternal(String templateId, Map<String, Object> params, String toUsers);

}
