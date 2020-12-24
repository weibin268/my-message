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

import java.util.Map;

@Component
public class MsgSender extends BaseMessageSender {

    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageTemplateService messageTemplateService;

    @Override
    public SendResult sendInternal(String templateId, Map<String, Object> params, String toUsers) {
        SendResult result = new SendResult();
        String content = messageTemplateService.resolveBydId(templateId);
        Object oMsgType = params.get(MsgParams.MSG_TYPE);
        Object oTitle = params.get(MsgParams.MSG_TITLE);
        Object oUrl = params.get(MsgParams.MSG_URL);
        Object oBizTable = params.get(MsgParams.MSG_BIZ_TABLE);
        Object obBizId = params.get(MsgParams.MSG_BIZ_ID);
        result.setData(content);
        messageService.add(MsgType.getByValue(Integer.valueOf(objectToString(oMsgType))), templateId, toUsers, objectToString(oTitle), content, objectToString(oUrl), objectToString(oBizTable), objectToString(obBizId));
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
