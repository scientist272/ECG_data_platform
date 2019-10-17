package com.cuhk.ksl.heart.service;

import com.alibaba.fastjson.JSON;
import com.cuhk.ksl.heart.vo.KafkaProducerMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class KafkaReceiver {
    private final UserDataService userDataService;
    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    @Autowired
    public KafkaReceiver(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @KafkaListener(topics = {"cluster-test"})
    public void listen(@Payload String record,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partitionId){
        Optional<?> messageValue = Optional.ofNullable(record);
        if(messageValue.isPresent()){
            log.info("----------success in getting message " +
                    "from kafka topic:{},with partitionId:{}-----------------",topic,partitionId);
            //异步处理
            threadPool.execute(()->{
                KafkaProducerMsg data = JSON.parseObject(record,KafkaProducerMsg.class);
                log.info("consume state:{}",userDataService.consumeUserData(data));//消费数据到数据库
            });

        }else{
            log.info("---------Failure in getting message from kafka-------------");
        }
    }
}
