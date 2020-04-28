package com.cuhk.ksl.heart.dao;

import com.cuhk.ksl.heart.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageDao extends JpaRepository<Message,Integer> {
    List<Message> getBySenderAndReceiverAndIsReceivedIsTrue(String sender,String receiver);
}
