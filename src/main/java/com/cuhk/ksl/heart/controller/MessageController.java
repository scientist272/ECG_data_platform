package com.cuhk.ksl.heart.controller;

import com.cuhk.ksl.heart.constant.CommonConstant;
import com.cuhk.ksl.heart.service.kafka.KafkaSender;
import com.cuhk.ksl.heart.util.DateUtil;
import com.cuhk.ksl.heart.vo.Msg;
import com.cuhk.ksl.heart.vo.heartMsg.HeartMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final KafkaSender kafkaSender;

    @Autowired
    public MessageController(KafkaSender kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @PostMapping("/send/{topic}")
    public Msg sendHeartMessage(@RequestBody HeartMsg heartMsg, @PathVariable String topic) {
        heartMsg.setRecordBase(DateUtil.generalSimpleDateFormat());
        kafkaSender.send(heartMsg,topic);
        return new Msg(CommonConstant.SUCCESS_CODE,CommonConstant.SEND_HEART_MESSAGE_SUCCESS);
    }
}