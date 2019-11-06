package com.cuhk.ksl.heart.vo.pm;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.List;

@Data
public class PMAirRecords {
    private String temperature;
    private String humidity;
    private String _1p0;
    private String _2p5;
    private String _10p;
    private String raw;
    private List<Integer> rawData;


    //静态工厂方法
    public static PMAirRecords generatePMAirRecords(String data){
        PMRecords pmRecords = JSON.parseObject(data,PMRecords.class);
        return JSON.parseObject(pmRecords.getAir(),PMAirRecords.class);
    }

    @Data
    private static class PMRecords{
        private String date;
        private String device;
        private String location;
        private String air;
    }
}
