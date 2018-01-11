package com.example.amqp.controller;

import com.example.amqp.entity.TaskTest;
import com.example.amqp.service.TaskTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ksb on 2018. 1. 6..
 */
@RestController
public class ApiController {

    @Autowired
    private TaskTestService taskTestService;

    @RequestMapping(method = RequestMethod.GET, value = "/data")
    public String data() {
        long start = System.currentTimeMillis();

        List<TaskTest> result1 = taskTestService.getFirstAll();
        List<TaskTest> result2 = taskTestService.getSecondAll();

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));

        return "data";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cache")
    public String cache() {
        long start = System.currentTimeMillis();

        taskTestService.initCache();

        List<TaskTest> result = taskTestService.getCacheAll();

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));

        return "cache";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find")
    public List<TaskTest> findAll() {
        return taskTestService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/save")
    public int insertOnDuplicate() {
        return taskTestService.insertOnDuplicate();
    }

}
