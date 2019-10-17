package com.cuhk.ksl.heart.vo;

import com.cuhk.ksl.heart.util.DateUtil;
import lombok.Data;


@Data
public class KafkaProducerMsg {
    private String user;
    private String timestamp;
    private String device;
    private String data;
}
