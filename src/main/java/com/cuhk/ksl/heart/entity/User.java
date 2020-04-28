package com.cuhk.ksl.heart.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(exclude = "friends")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @JSONField(serialize = false,deserialize = false)
    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JSONField(serialize = false,deserialize = false)
    private List<Role> roles;

    @JSONField(serialize = false,deserialize = false)
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<UserData> records;

    @JSONField(serialize = false,deserialize = false)
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Message> messages;

    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JSONField(serialize = false,deserialize = false)
    private Set<User> friends;

}
