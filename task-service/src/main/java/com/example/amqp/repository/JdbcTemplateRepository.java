package com.example.amqp.repository;

import com.example.amqp.entity.TaskTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ksb on 2018. 1. 7..
 */
@Repository
public class JdbcTemplateRepository {

    @Autowired
    @Qualifier("testJdbcTemplate")
    private JdbcTemplate testJdbcTemplate;

    public List<TaskTest> findAll() {
        List<TaskTest> list = testJdbcTemplate.query("select id, name, task, test from task_test",
                (rs, rowNum) -> {
                    TaskTest taskTest = new TaskTest();
                    taskTest.setId(rs.getLong("id"));
                    taskTest.setName(rs.getString("name"));
                    taskTest.setName(rs.getString("task"));
                    taskTest.setName(rs.getString("test"));

                    return taskTest;
                });

        return list;
    }

}
