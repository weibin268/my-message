package com.zhuang.message.enums;

import java.util.Arrays;

public enum NoticeStatus {

    TODO(0, "待处理"),
    FINISH(1, "已处理");

    private Integer value;
    private String name;

    NoticeStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public static NoticeStatus getByValue(Integer value) {
        return Arrays.stream(NoticeStatus.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
    }
}
