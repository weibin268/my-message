package com.zhuang.message.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuang.message.enums.MessageLogStatus;
import com.zhuang.message.enums.MessageType;
import com.zhuang.message.mapper.MessageLogMapper;
import com.zhuang.message.model.MessageLog;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class MessageLogService extends ServiceImpl<MessageLogMapper, MessageLog> {

    public void add(MessageType messageType, String templateId, Map params, String toUsers, boolean success, String result) {
        add(messageType, templateId, params, toUsers, null, success, result);
    }

    public void add(MessageType messageType, String templateId, Map params, String toUsers, String content, boolean success, String result) {
        MessageLog messageLog = new MessageLog();
        messageLog.setId(UUID.randomUUID().toString());
        messageLog.setType(messageType.getValue());
        messageLog.setTemplateId(templateId);
        messageLog.setParams(JSON.toJSONString(params));
        messageLog.setToUsers(toUsers);
        messageLog.setContent(content);
        messageLog.setStatus(success ? MessageLogStatus.SUCCESS.getValue() : MessageLogStatus.FAIL.getValue());
        messageLog.setResult(result);
        messageLog.setCreateTime(new Date());
    }
}
