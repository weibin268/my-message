package com.zhuang.message.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "my.message")
public class MyMessageProperties {

    private boolean enableLog = false;
    private String defaultSmsType = "sms";
    private final Sms sms = new Sms();
    private final Sms4Mas sms4Mas = new Sms4Mas();

    @Data
    public static class Sms {
        private String accessKeyId;
        private String accessSecret;
        private String signName;
    }

    @Data
    public static class Sms4Mas {
        private String baseUrl = "http://112.35.1.155:1992";
        private String ecName;
        private String apId;
        private String secretKey;
        private String sign;
        private String addSerial;
    }
}
