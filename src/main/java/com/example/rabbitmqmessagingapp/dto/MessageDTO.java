package com.example.rabbitmqmessagingapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for chat messages.
 * Validation ensures that both sender and content are provided.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    private Long id;

    @NotBlank(message = "Sender cannot be null or empty")
    @Size(max = 50, message = "Sender name must not exceed 50 characters")
    private String sender;

    private String receiver = "public";

    @NotBlank(message = "Content cannot be null or empty")
    @Size(max = 500, message = "Message content must not exceed 500 characters")
    private String content;

    private String messageId;

    // Convenience constructors (overloads)
    public MessageDTO(String sender, String receiver, String content, String messageId) {
        this(null, sender, receiver, content, messageId);
    }

    public MessageDTO(String sender, String receiver, String content) {
        this(null, sender, receiver, content, null);
    }
}
