package com.example.amqp.topic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ksb on 2018. 1. 6..
 */
@RestController
public class ApiController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(method = RequestMethod.GET, value="/test")
    public String send() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String[] serviceNames = {"test1Service", "test2Service", "test3Service", "test4Service"};
        MigrationRequest test = new MigrationRequest();
        test.setReset(true);
        test.setServices(serviceNames);

        rabbitTemplate.convertAndSend(MigrationEventListener.QUEUE_NAME, null, objectMapper.writeValueAsString(test));

        return "test";
    }

}
