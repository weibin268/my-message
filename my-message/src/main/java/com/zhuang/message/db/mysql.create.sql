create table sys_message_log
(
    id          varchar(36)  not null,
    type        varchar(10)  null comment '消息类型',
    template_id varchar(36)  null comment '模板Id',
    params      varchar(500) null comment '参数',
    to_users    varchar(500) null comment '接收用户',
    content     varchar(200) null comment '消息内容',
    result      varchar(500) null comment '发送结果',
    status      int          null comment '状态：0=失败；1=成功；',
    create_time DATETIME,
    modify_time DATETIME,
    create_by   VARCHAR(50),
    modify_by   VARCHAR(50),
    primary key (id)
)
    comment '消息日志';

create table sys_message_template
(
    id          varchar(36)  not null,
    name        varchar(10)  null comment '模板名字',
    content     varchar(200) null comment '模板内容',
    params      varchar(500) null comment '参数',
    status      int          null comment '状态：0=启用；1=禁用；',
    create_time DATETIME,
    modify_time DATETIME,
    create_by   VARCHAR(50),
    modify_by   VARCHAR(50),
    primary key (id)
)
    comment '消息模板';

