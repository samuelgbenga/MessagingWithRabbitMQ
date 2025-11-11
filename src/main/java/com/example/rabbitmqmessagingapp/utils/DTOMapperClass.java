package com.example.rabbitmqmessagingapp.utils;

import com.example.rabbitmqmessagingapp.entity.Message;
import com.example.rabbitmqmessagingapp.dto.MessageDTO;

public class DTOMapperClass {

    public static MessageDTO toDto(Message message) {
       return new MessageDTO(message.getId(), message.getSender(), message.getReceiver(),  message.getContent(), message.getMessageId());
    }


    public static Message toEntity(MessageDTO messageDTO) {
        return new Message(messageDTO.id(),  messageDTO.sender(), messageDTO.receiver(), messageDTO.content(), messageDTO.messageId());
    }

}
