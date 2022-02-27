package com.zhuang.message.service;

import com.zhuang.message.MyMessageTestApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class NoticeServiceTest extends MyMessageTestApplicationTest {


    @Autowired
    private NoticeService noticeService;

    @Test
    public void finish() {
        noticeService.finish("c3c693f5-2848-41aa-b8ef-c0bdc2515635");
    }

}