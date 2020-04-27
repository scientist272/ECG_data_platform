package com.cuhk.ksl.heart.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Message {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @JSONField(deserialize = false)
    private int id;
    @Column
    private String receiver;
    @Column(name = "picture_url")
    private String pictureUrl;
    @Column
    private String sender;
    @Column
    private String title;
    @Column
    private String content;
    @Column(name = "record_base")
    private String recordBase;

    @JSONField(serialize = false,deserialize = false)
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.MERGE},optional = false)
    private User user;

    @Column(name = "is_received")
    @JSONField(deserialize = false,serialize = false)
    private boolean isReceived;
}
