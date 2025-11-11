package com.example.rabbitmqmessagingapp.controller;

import com.example.rabbitmqmessagingapp.dto.MessageDTO;
import com.example.rabbitmqmessagingapp.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {


    private final IMessageService messageService;

    @PostMapping("/send")
    public String sendMessage(@Validated @RequestBody MessageDTO message) {

        messageService.sendMessage(message);

        return "Message sent to " + message.getReceiver();
    }
}
