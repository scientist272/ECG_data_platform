package com.cuhk.ksl.heart.service.impl;

import com.cuhk.ksl.heart.dao.UserRepo;
import com.cuhk.ksl.heart.entity.Role;
import com.cuhk.ksl.heart.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public UserDetailServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepo.findByUserName(s);
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = user.getRoles();
        if(user==null){
            throw new UsernameNotFoundException("User doesn't exist");
        }
        for(Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }


        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(),authorities);
    }
}
