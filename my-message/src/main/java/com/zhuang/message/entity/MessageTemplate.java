package com.zhuang.message.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_message_template")
public class MessageTemplate {

    private static final long serialVersionUID = 1L;
    @TableId
    private String id;
    private String name;
    private String content;
    private String params;
    private Integer status;
    private Date createTime;
    private Date modifyTime;
    private String createBy;
    private String modifyBy;

}
