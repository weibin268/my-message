package com.zhuang.message.impl;

import com.zhuang.message.BaseMessageSender;
import com.zhuang.message.constant.NoticeParams;
import com.zhuang.message.enums.MessageType;
import com.zhuang.message.enums.NoticeType;
import com.zhuang.message.model.SendResult;
import com.zhuang.message.service.NoticeService;
import com.zhuang.message.service.MessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class NoticeSender extends BaseMessageSender {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private MessageTemplateService messageTemplateService;

    @Override
    public SendResult sendInternal(String templateId, Map<String, Object> params, String toUsers) {
        if (StringUtils.isEmpty(toUsers)) throw new RuntimeException("toUsers is empty!");
        SendResult result = new SendResult();
        String content = messageTemplateService.resolveContent(templateId, params);
        Object oMsgType = params.get(NoticeParams.NOTICE_TYPE);
        Object oTitle = params.get(NoticeParams.NOTICE_TITLE);
        Object oUrl = params.get(NoticeParams.NOTICE_URL);
        Object oBizTable = params.get(NoticeParams.NOTICE_BIZ_TABLE);
        Object oBizId = params.get(NoticeParams.NOTICE_BIZ_ID);
        Object oFromUser = params.get(NoticeParams.NOTICE_FROM_USER);
        result.setContent(content);
        List<String> toUserList = Arrays.asList(toUsers.split(","));
        toUserList.forEach(toUser -> {
            noticeService.add(NoticeType.getByValue(Integer.valueOf(objectToString(oMsgType))), templateId, objectToString(oFromUser), toUser, objectToString(oTitle), content, objectToString(oUrl), objectToString(oBizTable), objectToString(oBizId));
        });
        return result;
    }

    private String objectToString(Object o) {
        return o == null ? null : o.toString();
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.NOTICE;
    }
}
