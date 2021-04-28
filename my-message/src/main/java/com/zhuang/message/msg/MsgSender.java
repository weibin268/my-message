package com.zhuang.message.msg;

import com.zhuang.message.BaseMessageSender;
import com.zhuang.message.constant.MsgParams;
import com.zhuang.message.enums.MessageType;
import com.zhuang.message.enums.MsgType;
import com.zhuang.message.model.SendResult;
import com.zhuang.message.service.MessageService;
import com.zhuang.message.service.MessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class MsgSender extends BaseMessageSender {

    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageTemplateService messageTemplateService;

    @Override
    public SendResult sendInternal(String templateId, Map<String, Object> params, String toUsers) {
        if (StringUtils.isEmpty(toUsers)) throw new RuntimeException("toUsers is empty!");
        SendResult result = new SendResult();
        String content = messageTemplateService.resolveContent(templateId, params);
        Object oMsgType = params.get(MsgParams.MSG_TYPE);
        Object oTitle = params.get(MsgParams.MSG_TITLE);
        Object oUrl = params.get(MsgParams.MSG_URL);
        Object oBizTable = params.get(MsgParams.MSG_BIZ_TABLE);
        Object oBizId = params.get(MsgParams.MSG_BIZ_ID);
        Object oFromUser = params.get(MsgParams.MSG_FROM_USER);
        result.setContent(content);
        List<String> toUserList = Arrays.asList(toUsers.split(","));
        toUserList.forEach(toUser -> {
            messageService.add(MsgType.getByValue(Integer.valueOf(objectToString(oMsgType))), templateId, objectToString(oFromUser), toUser, objectToString(oTitle), content, objectToString(oUrl), objectToString(oBizTable), objectToString(oBizId));
        });
        return result;
    }

    private String objectToString(Object o) {
        return o == null ? null : o.toString();
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.MSG;
    }
}
