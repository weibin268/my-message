package com.zhuang.message;

import java.util.Map;

public interface MessageSender {

    boolean send(String templateId, Map<String, Object> params, String toUsers);

}
