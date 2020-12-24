package com.zhuang.message.enums;

import java.util.Arrays;

public enum MessageLogStatus {

    SUCCESS(1,"发送成功"),
    FAIL(0,"发送失败");

    private Integer value;
    private String name;

    MessageLogStatus(Integer value, String name){
        this.value=value;
        this.name=name;
    }

    public Integer getValue(){
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public static MessageLogStatus getByValue(Integer value){
        return  Arrays.stream(MessageLogStatus.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
    }
}
