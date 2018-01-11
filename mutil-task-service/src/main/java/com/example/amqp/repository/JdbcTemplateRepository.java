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
                    taskTest.setTask(rs.getString("task"));
                    taskTest.setTest(rs.getString("test"));

                    return taskTest;
                });

        return list;
    }

//    ON DUPLICATE KEY UPDATE의 경우 행이 새 행으로 삽입되는 경우 행 당 영향을받는 행 값은 1이고 기존 행이 업데이트되는 경우 2입니다.
    public int insertOnDuplicate() {
//        String sql = "INSERT INTO task_test (id, name, task, test) VALUES (1, 'name1', 'task1', 'test1') ON DUPLICATE KEY UPDATE name='name2', task='task2', test='test2'";
//        return this.testJdbcTemplate.update(sql);
        String sql = "INSERT INTO task_test (id, name, task, test) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE name=?, task=?, test=?";
        return this.testJdbcTemplate.update(sql, 1, "name1", "task1", "test1", "name2", "task2", "test2");
    }

}
