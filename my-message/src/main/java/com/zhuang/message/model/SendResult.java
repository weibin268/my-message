package com.zhuang.message.model;

import lombok.Data;

@Data
public class SendResult {

    private boolean success;
    private String message;
    private String content;

}
