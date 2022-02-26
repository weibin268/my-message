package com.zhuang.message.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    private String id;
    private Integer type;
    private String templateId;
    private String fromUser;
    private String toUser;
    private String title;
    private String content;
    private String url;
    private String bizTable;
    private String bizId;
    private Integer status;
    private Date createTime;
    private Date modifyTime;
    private String createBy;
    private String modifyBy;

}
