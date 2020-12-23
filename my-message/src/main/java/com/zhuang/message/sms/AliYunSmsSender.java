package com.zhuang.message.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zhuang.message.MessageSender;
import com.zhuang.message.config.MyMessageProperties;
import com.zhuang.message.enums.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AliYunSmsSender implements MessageSender {

    @Autowired
    private MyMessageProperties myMessageProperties;

    @Override
    public boolean send(String templateId, Map<String, Object> params, String toUsers) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", myMessageProperties.getSms().getAccessKeyId(), myMessageProperties.getSms().getAccessSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("SignName", myMessageProperties.getSms().getSignName());
        request.putQueryParameter("PhoneNumbers", toUsers);
        request.putQueryParameter("TemplateCode", templateId);

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.SMS;
    }

}
