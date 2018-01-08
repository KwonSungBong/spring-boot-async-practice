package com.example.amqp.topic;

import lombok.Data;

/**
 * Created by ksb on 2018. 1. 8..
 */
@Data
public class MigrationRequest {

    private String[] services;
    private boolean reset;

}
