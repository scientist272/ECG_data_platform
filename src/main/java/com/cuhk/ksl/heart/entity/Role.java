package com.cuhk.ksl.heart.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "roles")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
}
