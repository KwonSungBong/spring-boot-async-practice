package com.example.demo.migration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by whilemouse on 18. 1. 8.
 */
@RequestMapping("/migration")
@RestController
public class MigrationController {

    @Autowired
    private MigrationService migrationService;

    @RequestMapping(method = RequestMethod.GET)
    public String test() throws JsonProcessingException {

        migrationService.migration();

        return "migration";
    }

}
