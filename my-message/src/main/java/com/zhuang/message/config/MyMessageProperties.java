package com.zhuang.message.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "my.message")
public class MyMessageProperties {

    private boolean enableLog = false;

    private final Sms sms = new Sms();

    @Data
    public static class Sms {
        private String accessKeyId;
        private String accessSecret;
        private String signName;
    }
}
