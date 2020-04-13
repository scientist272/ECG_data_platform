package com.cuhk.ksl.heart.service.kafka;

import com.alibaba.fastjson.JSON;
import com.cuhk.ksl.heart.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class KafkaSender {

    private final KafkaTemplate kafkaTemplate;

    @Autowired
    public KafkaSender(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Message msg, String topic) {
        String message = JSON.toJSONString(msg);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("--------------Fail in sending message:{} ,ex:{},topic:{}",msg.getTitle(),throwable,topic);
            }

            @Override
            public void onSuccess(SendResult<String, String> sendResult) {
                log.info("--------------Success in sending message:{} ,topic:{}",sendResult.getProducerRecord().toString(),sendResult.getRecordMetadata().topic());

            }
        });
    }
}
