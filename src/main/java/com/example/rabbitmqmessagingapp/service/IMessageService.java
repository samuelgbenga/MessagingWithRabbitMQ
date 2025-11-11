package com.example.rabbitmqmessagingapp.service;

import com.example.rabbitmqmessagingapp.dto.MessageDTO;

public interface IMessageService {
    String sendMessage(MessageDTO message);
}
