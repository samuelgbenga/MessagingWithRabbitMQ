package com.example.rabbitmqmessagingapp.rabbitmq.producer;


import com.example.rabbitmqmessagingapp.dto.MessageDTO;
import com.example.rabbitmqmessagingapp.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {


    // i was a able to send java object with this not just string
    @Autowired
    @Qualifier("customRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(MessageDTO message, String routingKey) {

       // String routingKey = "chat.message." + message.receiver(); // e.g., chat.message.john
        //rabbitTemplate.convertAndSend("amq.topic", routingKey, message); // for stomp
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, routingKey, message); // to publish in the logs optional
        System.out.println(" [Producer] Sent to " + message.receiver() + ": " + message.content());
    }
}
