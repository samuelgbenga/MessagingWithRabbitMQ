package com.example.rabbitmqmessagingapp.repo;


import com.example.rabbitmqmessagingapp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message,Long> {


}
