package com.cuhk.ksl.heart;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KslApplication {

    public static void main(String[] args) {
        SpringApplication.run(KslApplication.class, args);
    }
//    @Bean
//    public NewTopic testTopic2(){
//        return new NewTopic("test1",2,(short)1);
    }

