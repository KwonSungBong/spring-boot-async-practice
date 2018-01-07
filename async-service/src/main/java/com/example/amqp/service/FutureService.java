package com.example.amqp.service;

import com.example.amqp.config.DelayUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by ksb on 2018. 1. 7..
 */
@Service
public class FutureService {

    public String call(String string) {
        final String foo = new Date() + ": call" + string;
        System.out.println(foo);
        return foo;
    }

    public String delayCall(String string) {
        DelayUtil.delay();
        final String foo = new Date() + ": delay call" + string;
        System.out.println(foo);
        return foo;
    }

}
