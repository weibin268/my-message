package com.zhuang.message.enums;

public enum MessageType {

    SMS("sms"),
    NOTICE("notice");

    private String value;

    MessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
