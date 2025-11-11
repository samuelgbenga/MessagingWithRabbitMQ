package com.example.rabbitmqmessagingapp.dto;


public record MessageDTO (Long id, String sender, String receiver, String content, String messageId) {

     // what is the possibility that two users with same name already exist
    // user name must not be thesame. no two same user must exist in the db. (solution)
    // how do you differentiate

    MessageDTO (String sender, String receiver, String content, String messageId) {
        this(null, sender, receiver, content, messageId);
    }

    // to ignore id upon request
    MessageDTO (String sender, String receiver, String content){
        this(null, sender, receiver, content, null);
    }


}

