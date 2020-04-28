package com.cuhk.ksl.heart.service.impl;

import com.cuhk.ksl.heart.constant.CommonConstant;
import com.cuhk.ksl.heart.dao.MessageDao;
import com.cuhk.ksl.heart.dao.UserRepo;
import com.cuhk.ksl.heart.entity.Message;
import com.cuhk.ksl.heart.service.MessageService;
import com.cuhk.ksl.heart.service.kafka.KafkaSender;
import com.cuhk.ksl.heart.util.DateUtil;
import com.cuhk.ksl.heart.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {
    private final KafkaSender kafkaSender;
    private final MessageDao messageDao;
    private final UserRepo userRepo;
    @Autowired
    public MessageServiceImpl(KafkaSender kafkaSender, MessageDao messageDao, UserRepo userRepo) {
        this.kafkaSender = kafkaSender;
        this.messageDao = messageDao;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public Msg sendMessage(Message message,String topic) {
        message.setRecordBase(DateUtil.generalSimpleDateFormat());
        message.setUser(userRepo.findByUserName(message.getSender()));
        message.setReceived(false);
        message.setId(messageDao.save(message).getId());
        kafkaSender.send(message,topic);
        return new Msg(CommonConstant.SUCCESS_CODE,CommonConstant.SEND_HEART_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public Msg getMessageBySenderAndReceiver(String sender, String receiver) {
        return new Msg(CommonConstant.SUCCESS_CODE,messageDao.getBySenderAndReceiverAndIsReceivedIsTrue(sender,receiver));
    }
}
