package com.cuhk.ksl.heart.dao;

import com.cuhk.ksl.heart.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
