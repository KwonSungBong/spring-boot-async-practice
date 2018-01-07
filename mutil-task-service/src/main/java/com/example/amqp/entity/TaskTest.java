package com.example.amqp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ksb on 2018. 1. 7..
 */
@Entity
@Data
public class TaskTest  implements Serializable {

    public static final int TYPE_ID = 10000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String task;
    private String test;

}
