package com.example.rabbitmqmessagingapp.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Exchange name (topic exchange supports routing by pattern)
    public static final String EXCHANGE_NAME = "chat.exchange";

    // Queue prefix — each user will have their own queue
    public static final String QUEUE_PREFIX = "chat.queue.";

    // Routing key pattern: chat.message.<receiver>
    public static final String ROUTING_KEY_PATTERN = "chat.message.#";

    @Bean
    public TopicExchange chatExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // Example default queue for testing
    @Bean
    public Queue defaultQueue() {
        return new Queue(QUEUE_PREFIX + "default");
    }

    @Bean
    public Binding defaultBinding(Queue defaultQueue, TopicExchange chatExchange) {
        return BindingBuilder.bind(defaultQueue).to(chatExchange).with(ROUTING_KEY_PATTERN);
    }

    // ✅ Add this bean to enable JSON conversion
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // ✅ Make RabbitTemplate use JSON converter
    @Bean
    public RabbitTemplate customRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
