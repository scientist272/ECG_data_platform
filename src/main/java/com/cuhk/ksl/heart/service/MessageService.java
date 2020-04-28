package com.cuhk.ksl.heart.service;

import com.cuhk.ksl.heart.entity.Message;
import com.cuhk.ksl.heart.vo.Msg;

public interface MessageService {
    Msg sendMessage(Message message,String topic);
    Msg getMessageBySenderAndReceiver(String sender,String receiver);
}
