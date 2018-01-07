package com.example.amqp.repository;

import com.example.amqp.entity.TaskTest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ksb on 2018. 1. 7..
 */
public interface TaskTestRepository extends JpaRepository<TaskTest, Long> {

    TaskTest findByName(String name);

}