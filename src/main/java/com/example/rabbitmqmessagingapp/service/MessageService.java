package com.example.rabbitmqmessagingapp.service;

import com.example.rabbitmqmessagingapp.dto.MessageDTO;
import com.example.rabbitmqmessagingapp.rabbitmq.consumer.RabbitMQConsumer;
import com.example.rabbitmqmessagingapp.rabbitmq.producer.RabbitMQProducer;
import com.example.rabbitmqmessagingapp.repo.MessageRepo;
import com.example.rabbitmqmessagingapp.utils.DTOMapperClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {

    private final RabbitMQProducer producer;
    private final MessageRepo messageRepo;

    @Override
    public String sendMessage(MessageDTO message) {

        // logic
        // handle private and public messages.
        // aslong as it the receiver is null/not specified make public the exchange key route
        // if the user is not specified merge both names in ascending order then make the exchange key route

        // generate messageId
        String messageId = generateMessageId(message);
        // save to db asynchronously
        // 🔹 2. Save message asynchronously to DB
        CompletableFuture.runAsync(() -> {
            // Convert DTO to entity
            var entity = DTOMapperClass.toEntity(message);

            // if receiver is null set to public
//            if (message.getReceiver() == null || message.getReceiver().isBlank())
//                    entity.setReceiver("public");

            // Set the generated message ID
            entity.setMessageId(messageId);

            // Save to DB
            messageRepo.save(entity);

            System.out.println("[Async] Saved message from " + message.getSender() + " to DB.");
        });

        // publish
        producer.sendMessage(message, generateRoutingKey(message) );

        return "String";
    }


    private String generateMessageId(MessageDTO message) {
        String messageId;

        // 🔹 1. Determine routing key logic
        if (message.getReceiver() == null || message.getReceiver().isBlank()) {
            // Public message (no receiver)
            messageId = "message.public";
        } else {
            // Private chat: ensure consistent routing key order
            String sender = message.getSender().toLowerCase();
            String receiver = message.getReceiver().toLowerCase();

            // Merge both names in ascending order (so userA-userB == userB-userA)
            String mergedKey = (sender.compareTo(receiver) < 0)
                    ? sender + "." + receiver
                    : receiver + "." + sender;

            messageId = "message." + mergedKey;
        }

        return messageId;
    }

    private String generateRoutingKey(MessageDTO message) {
//        if (message.getReceiver() == null || message.getReceiver().isBlank()) {
//            return "chat.message.public";
//        }
        return "chat.message." + message.getReceiver().toLowerCase();
    }
}
