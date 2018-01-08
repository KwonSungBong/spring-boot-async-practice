package com.example.demo.migration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ksb on 2018. 1. 8..
 */
@Service
public class MigrationService {

    @Autowired
    private RabbitTemplate template;

    public void migration() throws JsonProcessingException {
        String[] serviceNames = {"test1Service", "test2Service", "test3Service", "test4Service"};
        MigrationRequest migrationRequest = new MigrationRequest();
        migrationRequest.setReset(true);
        migrationRequest.setServices(serviceNames);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(migrationRequest);

        template.convertAndSend(QueueConfig.QUEUE_NAME, json);
    }

}
