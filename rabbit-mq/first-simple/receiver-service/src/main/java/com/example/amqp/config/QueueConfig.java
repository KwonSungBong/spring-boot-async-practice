package com.example.amqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Date;

/**
 * Created by whilemouse on 17. 8. 29.
 */
@Configuration
@RabbitListener(queues = "foo")
public class QueueConfig {

    @Bean
    public Queue queue() {
        return new Queue("foo");
    }

    @RabbitHandler
    public void process(@Payload String foo) {
        System.out.println(new Date() + ": " + foo);
    }

}
