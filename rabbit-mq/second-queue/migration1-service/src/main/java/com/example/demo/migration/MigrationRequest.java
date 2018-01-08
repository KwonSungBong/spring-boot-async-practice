package com.example.demo.migration;

import lombok.Data;

/**
 * Created by whilemouse on 18. 1. 8.
 */
@Data
public class MigrationRequest {

    private String[] services;
    private boolean reset;

}
