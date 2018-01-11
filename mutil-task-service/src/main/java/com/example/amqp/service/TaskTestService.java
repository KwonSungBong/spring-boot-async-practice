package com.example.amqp.service;

import com.example.amqp.entity.TaskTest;
import com.example.amqp.repository.CacheRepository;
import com.example.amqp.repository.JdbcTemplateRepository;
import com.example.amqp.repository.TaskTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ksb on 2018. 1. 7..
 */
@Service
public class TaskTestService {

    @Autowired
    private TaskTestRepository taskTestRepository;

    @Autowired
    private JdbcTemplateRepository jdbcTemplateRepository;

    @Autowired
    private CacheRepository cacheRepository;

    public List<TaskTest> getFirstAll() {
        return taskTestRepository.findAll();
    }

    public List<TaskTest> getSecondAll() {
        return jdbcTemplateRepository.findAll();
    }

    public List<TaskTest> getCacheAll() {
        return cacheRepository.findAll();
    }

    public void initCache() {
        TaskTest taskTest1 = new TaskTest();
        taskTest1.setId(1L);
        taskTest1.setName("taskTest1 setName");
        taskTest1.setTask("taskTest1 setTask");
        taskTest1.setTest("taskTest1 setTest");

        TaskTest taskTest2 = new TaskTest();
        taskTest2.setId(2L);
        taskTest2.setName("taskTest2 setName");
        taskTest2.setTask("taskTest2 setTask");
        taskTest2.setTest("taskTest2 setTest");

        TaskTest taskTest3 = new TaskTest();
        taskTest3.setId(3L);
        taskTest3.setName("taskTest3 setName");
        taskTest3.setTask("taskTest3 setTask");
        taskTest3.setTest("taskTest3 setTest");

        List<TaskTest> taskTests = Arrays.asList(taskTest1, taskTest2, taskTest3);

        cacheRepository.saveList(taskTests);
    }

    public List<TaskTest> findAll() {
        return jdbcTemplateRepository.findAll();
    }

    public int insertOnDuplicate() {
        return jdbcTemplateRepository.insertOnDuplicate();
    }

}
