package com.zhuang.message.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_notice_user")
public class NoticeUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    private String id;
    private String noticeId;
    private String userId;
    private Date createTime;

}
