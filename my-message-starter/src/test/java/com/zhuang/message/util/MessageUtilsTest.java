package com.zhuang.message.util;

import com.zhuang.message.MyMessageTestApplicationTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MessageUtilsTest extends MyMessageTestApplicationTest {

    @Test
    public void sendSms() {
        Map<String, Object> params = new HashMap<>();
        params.put("code", "22222");
        MessageUtils.sendSms("", params, "13798106142");
    }
}
