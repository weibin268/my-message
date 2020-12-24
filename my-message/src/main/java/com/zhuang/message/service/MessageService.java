package com.zhuang.message.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuang.message.mapper.MessageMapper;
import com.zhuang.message.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends ServiceImpl<MessageMapper, Message> {


}
