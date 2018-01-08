package com.example.demo.migration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by whilemouse on 18. 1. 8.
 */
@Configuration
public class QueueConfig {

    public final static String QUEUE_NAME = "media.migration.queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

}
