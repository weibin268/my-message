package com.zhuang.message.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_message_log")
public class MessageLog {

    private static final long serialVersionUID = 1L;
    @TableId
    private String id;
    private String type;
    private String templateId;
    private String params;
    private String toUsers;
    private String content;
    private String result;
    private Integer status;
    private Date createTime;
    private String createBy;

}
