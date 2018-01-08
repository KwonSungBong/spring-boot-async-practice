package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by whilemouse on 18. 1. 8.
 */
@RequestMapping("/migration")
@RestController
public class MigrationController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String[] serviceNames = {"test1Service", "test2Service", "test3Service", "test4Service"};
        MigrationRequest test = new MigrationRequest();
        test.setReset(true);
        test.setServices(serviceNames);

        rabbitTemplate.convertAndSend(MigrationEventListener.QUEUE_NAME, null, objectMapper.writeValueAsString(test));

        return "test";
    }

}
