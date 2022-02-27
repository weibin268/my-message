package com.zhuang.message.enums;

import java.util.Arrays;

public enum NoticeType {

    TO_READ(0,"待阅消息"),
    TO_DO(1,"代办消息");

    private Integer value;
    private String name;

    NoticeType(Integer value, String name){
        this.value=value;
        this.name=name;
    }

    public Integer getValue(){
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public static NoticeType getByValue(Integer value){
        return  Arrays.stream(NoticeType.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
    }
}
