package com.example.rabbitmqmessagingapp.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageReq {

    private Long id;
    private String sender;
    private String receiver;
    private String content;
}
