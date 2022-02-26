package com.zhuang.message.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuang.message.enums.CommonStatus;
import com.zhuang.message.mapper.MessageTemplateMapper;
import com.zhuang.message.entity.MessageTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageTemplateService extends ServiceImpl<MessageTemplateMapper, MessageTemplate> {

    public String resolveContent(String id, Map<String, Object> params) {
        MessageTemplate messageTemplate = getById(id);
        if (messageTemplate == null || !messageTemplate.getStatus().equals(CommonStatus.ENABLE.getValue())) {
            throw new RuntimeException("模板id{" + id + "}未找到对应的消息模板！");
        }
        String content = messageTemplate.getContent();
        for (Map.Entry entry : params.entrySet()) {
            Object value = entry.getValue();
            String strValue = value == null ? "" : value.toString();
            content = content.replace("${" + entry.getKey() + "}", strValue);
        }
        return content;
    }

}
