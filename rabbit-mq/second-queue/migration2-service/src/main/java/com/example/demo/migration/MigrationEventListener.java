package com.example.demo.migration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * Created by whilemouse on 18. 1. 8.
 */
@Slf4j
@Configuration
@RabbitListener(queues = QueueConfig.QUEUE_NAME)
public class MigrationEventListener {

    private ObjectMapper objectMapper = new ObjectMapper();

    @RabbitHandler
    public void receive(String message) {
        try {
            MigrationRequest migrationRequest = objectMapper.readValue(message, MigrationRequest.class);
            log.info("received vendor migration msg: {}", migrationRequest);
        } catch (Exception e) {
            log.info("received vendor migration error msg: {}", e.getMessage());
        }
    }

}
