package com.cuhk.ksl.heart.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    @Column(name="start_time")
    private String startTime;
    @Column(name = "device")
    private String device;

    @Lob
    @Column(name = "data",columnDefinition = "MEDIUMTEXT")
    private String data;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.MERGE},optional = false)
    private User user;
}
