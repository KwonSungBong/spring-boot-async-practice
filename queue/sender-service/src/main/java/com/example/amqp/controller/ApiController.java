package com.example.amqp.controller;

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

    @RequestMapping(method = RequestMethod.GET, value="/send")
    public String send() {
        this.rabbitTemplate.convertAndSend("foo", "hello");
        return "send";
    }

}
