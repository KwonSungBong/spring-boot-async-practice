package com.example.amqp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * Created by whilemouse on 17. 8. 29.
 */
@Slf4j
@Configuration
@RabbitListener(queues = "foo")
@EnableScheduling
public class QueueConfig {

    @Bean
    public Queue queue() {
        return new Queue("foo");
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 1000)
    public void send() {
        this.rabbitTemplate.convertAndSend("foo", "hello");
    }

    @RabbitHandler
    public void process(@Payload String foo) {
        log.debug(new Date() + ": " + foo);
    }

}
