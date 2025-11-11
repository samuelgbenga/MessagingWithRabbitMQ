package com.example.rabbitmqmessagingapp.utils;

import com.example.rabbitmqmessagingapp.entity.Message;
import com.example.rabbitmqmessagingapp.dto.MessageDTO;

public class DTOMapperClass {

    public static MessageDTO toDto(Message message) {
       return new MessageDTO(message.getId(), message.getSender(), message.getReceiver(),  message.getContent(), message.getMessageId());
    }


    public static Message toEntity(MessageDTO messageDTO) {
        return new Message(messageDTO.getId(),  messageDTO.getSender(), messageDTO.getReceiver(), messageDTO.getContent(), messageDTO.getMessageId());
    }

    // the parttern for loading private is to message.
    // to recognize individual channel for respective users on same queue but different key routing with public / username...
    // so every individual username or public should be a unique key routing that can be sent to
    // then public should remain public.
    // how it is loaded in the frontend:
    // message between two users.
    // when you get it on the frontend then you decide what you want to do with it
    // what you have on your frontend is a set of array of objects . a map whose unique message id is the
    // message id between the different users and also public for puplic
    // i.e map (public -> [{sender: "", reciever: "", content: "", messageId: "public"},......], messageId -> [{...},....])
    // this would be cashed. so when a new message comes it. it is mapped to the uniqe message id or public (for public message)
    //


}
