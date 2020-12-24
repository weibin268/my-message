package com.zhuang.message.util;

import com.zhuang.message.MyMessageTestApplicationTest;
import com.zhuang.message.constant.MsgParams;
import com.zhuang.message.enums.MsgType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MessageUtilsTest extends MyMessageTestApplicationTest {

    @Test
    public void sendSms() {
        Map<String, Object> params = new HashMap<>();
        params.put("code", "22222");
        MessageUtils.sendSms("1111", params, "13798106142");
    }

    @Test
    public void sendMsg() {
        Map<String, Object> params = new HashMap<>();
        params.put(MsgParams.MSG_TITLE, "test");
        params.put("name", "zwb");
        params.put("age", 18);
        MessageUtils.sendMsg(MsgType.TO_READ, "1", params, "13798106142");
    }
}
