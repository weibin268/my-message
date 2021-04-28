package com.zhuang.message.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuang.message.enums.MsgStatus;
import com.zhuang.message.enums.MsgType;
import com.zhuang.message.mapper.MessageMapper;
import com.zhuang.message.model.Message;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class MessageService extends ServiceImpl<MessageMapper, Message> {

    public void add(MsgType msgType, String templateId, String fromUser, String toUser, String title, String content, String url, String bizTable, String bizId) {
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setType(msgType.getValue());
        message.setTemplateId(templateId);
        message.setFromUser(fromUser);
        message.setToUser(toUser);
        message.setTitle(title);
        message.setContent(content);
        message.setUrl(url);
        message.setBizTable(bizTable);
        message.setBizId(bizId);
        message.setStatus(MsgStatus.TODO.getValue());
        message.setCreateTime(new Date());
        save(message);
    }

    public void finish(String id) {
        update(new LambdaUpdateWrapper<Message>()
                .eq(Message::getId, id)
                .set(Message::getStatus, MsgStatus.FINISH.getValue())
                .set(Message::getModifyTime, new Date())
        );
    }

    public void finish(String bizTable, String bizId, String templateId) {
        update(new LambdaUpdateWrapper<Message>()
                .eq(Message::getBizTable, bizTable)
                .eq(Message::getBizId, bizId)
                .eq(Message::getTemplateId, templateId)
                .set(Message::getStatus, MsgStatus.FINISH.getValue())
                .set(Message::getModifyTime, new Date())
        );
    }

}
