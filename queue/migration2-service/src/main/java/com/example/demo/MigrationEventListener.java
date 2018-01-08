package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by whilemouse on 18. 1. 8.
 */
//@Profile("!local")
@Service
@Slf4j
public class MigrationEventListener {

    public final static String QUEUE_NAME = "media.migration.queue";

    private ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(autoDelete = "true", exclusive = "true"),
            exchange = @Exchange(value = QUEUE_NAME, autoDelete = "true"))
    )
    public void receiveMigrationEvent(Message message) throws IOException {
        MigrationRequest migrationRequest = objectMapper.readValue(message.getBody(), MigrationRequest.class);
        log.info("received vendor migration msg: {}", migrationRequest);
    }

}
