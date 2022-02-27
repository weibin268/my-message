
INSERT INTO sys_message_template (`id`, `name`, `content`, `params`, `status`, `create_time`, `modify_time`, `create_by`, `modify_by`)
VALUES ('1', 'test', 'hello ${name}!', '${name}', 1,now(), NULL, NULL, NULL);