package com.cuhk.ksl.heart.controller;

import com.cuhk.ksl.heart.entity.Message;
import com.cuhk.ksl.heart.service.MessageService;
import com.cuhk.ksl.heart.service.kafka.KafkaSender;
import com.cuhk.ksl.heart.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final KafkaSender kafkaSender;
    private final MessageService messageService;

    @Autowired
    public MessageController(KafkaSender kafkaSender, MessageService messageService) {
        this.kafkaSender = kafkaSender;
        this.messageService = messageService;
    }

    @PostMapping("/send/{topic}")
    public Msg sendHeartMessage(@RequestBody Message message, @PathVariable String topic) {

        return messageService.sendMessage(message,topic);
    }
}
