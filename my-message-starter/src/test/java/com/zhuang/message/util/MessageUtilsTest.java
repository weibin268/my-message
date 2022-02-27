package com.zhuang.message.util;

import com.zhuang.message.MyMessageTestApplicationTest;
import com.zhuang.message.constant.NoticeParams;
import com.zhuang.message.enums.NoticeType;
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
    public void sendNotice() {
        Map<String, Object> params = new HashMap<>();
        params.put(NoticeParams.NOTICE_TITLE, "test");
        params.put("name", "zwb");
        params.put("age", 18);
        MessageUtils.sendNotice(NoticeType.TO_READ, "1", params, "13798106142,1111111,222222");
    }
}
