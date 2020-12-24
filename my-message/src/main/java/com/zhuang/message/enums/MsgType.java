package com.zhuang.message.enums;

import java.util.Arrays;

public enum MsgType {

    TO_READ(0,"待阅消息"),
    TO_DO(1,"代办消息");

    private Integer value;
    private String name;

    MsgType(Integer value, String name){
        this.value=value;
        this.name=name;
    }

    public Integer getValue(){
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public static MsgType getByValue(Integer value){
        return  Arrays.stream(MsgType.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
    }
}
