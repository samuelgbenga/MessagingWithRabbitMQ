package com.example.rabbitmqmessagingapp.rabbitmq.consumer;


import com.example.rabbitmqmessagingapp.dto.MessageDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    // Example consumer for default queue
    @RabbitListener(queues = "chat.queue.default")
    public void receiveMessage(MessageDTO message) {
        System.out.println(" [Consumer] Received message for " + message.getReceiver() + ": " + message.getContent());
    }

    // You can dynamically create listeners for each user in a real app
}
