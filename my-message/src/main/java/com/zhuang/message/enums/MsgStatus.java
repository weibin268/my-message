package com.zhuang.message.enums;

import java.util.Arrays;

public enum MsgStatus {

    TODO(0, "待处理"),
    FINISH(1, "已处理");

    private Integer value;
    private String name;

    MsgStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public static MsgStatus getByValue(Integer value) {
        return Arrays.stream(MsgStatus.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
    }
}
