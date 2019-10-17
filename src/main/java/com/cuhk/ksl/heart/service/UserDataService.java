package com.cuhk.ksl.heart.service;

import com.cuhk.ksl.heart.vo.KafkaProducerMsg;

public interface UserDataService {
    String consumeUserData(KafkaProducerMsg userData);
}
