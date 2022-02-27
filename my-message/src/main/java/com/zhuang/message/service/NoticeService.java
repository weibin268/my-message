package com.zhuang.message.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuang.message.entity.Notice;
import com.zhuang.message.enums.NoticeStatus;
import com.zhuang.message.enums.NoticeType;
import com.zhuang.message.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class NoticeService extends ServiceImpl<NoticeMapper, Notice> {

    public void add(NoticeType noticeType, String templateId, String fromUser, String toUser, String title, String content, String url, String bizTable, String bizId) {
        Notice notice = new Notice();
        notice.setId(UUID.randomUUID().toString());
        notice.setType(noticeType.getValue());
        notice.setTemplateId(templateId);
        notice.setFromUser(fromUser);
        notice.setToUser(toUser);
        notice.setTitle(title);
        notice.setContent(content);
        notice.setUrl(url);
        notice.setBizTable(bizTable);
        notice.setBizId(bizId);
        notice.setStatus(NoticeStatus.TODO.getValue());
        notice.setCreateTime(new Date());
        save(notice);
    }

    public void finish(String id) {
        update(new LambdaUpdateWrapper<Notice>()
                .eq(Notice::getId, id)
                .set(Notice::getStatus, NoticeStatus.FINISH.getValue())
                .set(Notice::getModifyTime, new Date())
        );
    }

    public void finish(String bizTable, String bizId, String templateId) {
        update(new LambdaUpdateWrapper<Notice>()
                .eq(Notice::getBizTable, bizTable)
                .eq(Notice::getBizId, bizId)
                .eq(Notice::getTemplateId, templateId)
                .set(Notice::getStatus, NoticeStatus.FINISH.getValue())
                .set(Notice::getModifyTime, new Date())
        );
    }

}
