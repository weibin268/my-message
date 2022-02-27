create table sys_notice
(
    id          varchar(36)  not null,
    type        int  null comment '消息类型：0=待阅消息；1=代办消息；',
    template_id varchar(36)  null comment '消息模板Id',
    from_user    varchar(36) null comment '发送用户',
    to_user    varchar(36) null comment '接收用户',
    title       varchar(100)  null comment '消息标题',
    content     text null comment '消息内容',
    url         varchar (500) null comment '跳转url',
    biz_table   VARCHAR(50) null comment '业务表名',
    biz_id      VARCHAR(36) null comment '业务表Id',
    status      int          null comment '状态：0=待处理；1=已处理；',
    create_time DATETIME,
    modify_time DATETIME,
    create_by   VARCHAR(50),
    modify_by   VARCHAR(50),
    primary key (id)
)
    comment '通知消息';

create table sys_notice_user
(
    id          varchar(36)  not null,
    notice_id   varchar(36)  null comment '消息Id',
    user_id     varchar(36)  null comment '用户Id',
    create_time DATETIME,
    primary key (id)
)
    comment '消息用户关系';

create table sys_message_log
(
    id          varchar(36)  not null,
    type        varchar(10)  null comment '消息类型',
    template_id varchar(36)  null comment '消息模板Id',
    params      varchar(500) null comment '参数',
    to_users    varchar(1000) null comment '接收用户',
    content     text null comment '消息内容',
    result      varchar(1000) null comment '发送结果',
    status      int          null comment '状态：0=失败；1=成功；',
    create_time DATETIME,
    create_by   VARCHAR(50),
    primary key (id)
)
    comment '消息日志';

create table sys_message_template
(
    id          varchar(36)  not null,
    name        varchar(100)  null comment '模板名字',
    content     varchar(2000) null comment '模板内容',
    params      varchar(500) null comment '参数',
    status      int          null comment '状态：0=启用；1=禁用；',
    create_time DATETIME,
    modify_time DATETIME,
    create_by   VARCHAR(50),
    modify_by   VARCHAR(50),
    primary key (id)
)
    comment '消息模板';

