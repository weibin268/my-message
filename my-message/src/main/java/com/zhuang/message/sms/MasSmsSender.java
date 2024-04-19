package com.zhuang.message.sms;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.zhuang.message.BaseMessageSender;
import com.zhuang.message.config.MyMessageProperties;
import com.zhuang.message.constant.MsgParams;
import com.zhuang.message.enums.MessageType;
import com.zhuang.message.model.SendResult;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MasSmsSender extends BaseMessageSender {

    @Autowired
    private MyMessageProperties myMessageProperties;

    @Override
    public SendResult sendInternal(String templateId, Map<String, Object> params, String toUsers) {
        SendResult result = new SendResult();
        try {
            if ("*".equals(templateId)) {
                Submit4Normal submit = new Submit4Normal();
                submit.setMobiles(toUsers);
                submit.setContent(params.get(MsgParams.MSG_CONTENT).toString());
                String summit4Normal = getSummit4Normal(submit);
                Result r = post("/sms/norsubmit", summit4Normal);
                result.setSuccess(r.getSuccess());
                result.setMessage(r.getRspcod());
                result.setContent(r.getMsgGroup());
            } else {
                Submit4Template submit = new Submit4Template();
                submit.setMobiles(toUsers);
                submit.setTemplateId(templateId);
                List<String> keyList = params.keySet().stream().sorted().collect(Collectors.toList());
                List<String> paramList = new ArrayList<>();
                for (String key : keyList) {
                    paramList.add(params.get(key).toString());
                }
                submit.setParams(JSON.toJSONString(paramList));
                String summit4Template = getSummit4Template(submit);
                Result r = post("/sms/tmpsubmit", summit4Template);
                result.setSuccess(r.getSuccess());
                result.setMessage(r.getRspcod());
                result.setContent(r.getMsgGroup());
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Result post(String method, String content) {
        String post = HttpUtil.post(getFullUrl(method), content);
        Result r = JSON.parseObject(post, Result.class);
        return r;
    }

    public String getFullUrl(String method) {
        return myMessageProperties.getSms4Mas().getBaseUrl() + method;
    }

    public String getSummit4Normal(Submit4Normal submit) {
        MyMessageProperties.Sms4Mas sms4Mas = myMessageProperties.getSms4Mas();
        submit.setEcName(sms4Mas.getEcName());
        submit.setApId(sms4Mas.getApId());
        submit.setSecretKey(sms4Mas.getSecretKey());
        submit.setSign(sms4Mas.getSign());
        submit.setAddSerial(sms4Mas.getAddSerial());
        StringBuilder sb = new StringBuilder();
        sb.append(submit.getEcName());
        sb.append(submit.getApId());
        sb.append(submit.getSecretKey());
        sb.append(submit.getMobiles());
        sb.append(submit.getContent());
        sb.append(submit.getSign());
        sb.append(submit.getAddSerial());
        String mac = DigestUtils.md5DigestAsHex(sb.toString().getBytes(StandardCharsets.UTF_8));
        submit.setMac(mac);
        String summitBase64 = Base64.getEncoder().encodeToString(JSON.toJSONString(submit).getBytes(StandardCharsets.UTF_8));
        return summitBase64;
    }


    public String getSummit4Template(Submit4Template submit) {
        MyMessageProperties.Sms4Mas sms4Mas = myMessageProperties.getSms4Mas();
        submit.setEcName(sms4Mas.getEcName());
        submit.setApId(sms4Mas.getApId());
        submit.setSecretKey(sms4Mas.getSecretKey());
        submit.setSign(sms4Mas.getSign());
        submit.setAddSerial(sms4Mas.getAddSerial());
        StringBuilder sb = new StringBuilder();
        sb.append(submit.getEcName());
        sb.append(submit.getApId());
        sb.append(submit.getSecretKey());
        sb.append(submit.getTemplateId());
        sb.append(submit.getMobiles());
        sb.append(submit.getParams());
        sb.append(submit.getSign());
        sb.append(submit.getAddSerial());
        String mac = DigestUtils.md5DigestAsHex(sb.toString().getBytes(StandardCharsets.UTF_8));
        submit.setMac(mac);
        String summitBase64 = Base64.getEncoder().encodeToString(JSON.toJSONString(submit).getBytes(StandardCharsets.UTF_8));
        return summitBase64;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.SMS;
    }

    @Data
    public static class Submit4Normal extends Submit {
        private String content;
    }

    @Data
    public static class Submit4Template extends Submit {
        private String templateId;
        private String params;
    }

    @Data
    public static class Submit {
        private String ecName;
        private String apId;
        private String secretKey;
        private String mobiles;
        private String sign;
        private String addSerial;
        private String mac;
    }

    @Data
    public static class Result {
        private Boolean success;
        private String rspcod;
        private String msgGroup;
    }

}
