package com.zhuang.message.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my.message")
public class MyMessageProperties {

    private boolean enableLog = false;

    private final Sms sms = new Sms();

    public static class Sms {
        private String accessKeyId;
        private String accessSecret;
        private String signName;

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getAccessSecret() {
            return accessSecret;
        }

        public void setAccessSecret(String accessSecret) {
            this.accessSecret = accessSecret;
        }

        public String getSignName() {
            return signName;
        }

        public void setSignName(String signName) {
            this.signName = signName;
        }
    }

    public Sms getSms() {
        return sms;
    }

    public boolean getEnableLog() {
        return enableLog;
    }
}
