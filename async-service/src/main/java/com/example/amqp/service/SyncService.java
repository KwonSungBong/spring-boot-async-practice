package com.example.amqp.service;

import com.example.amqp.config.DelayUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by ksb on 2018. 1. 6..
 */
@Service
public class SyncService {

    public String call() {
        DelayUtil.delay();
        final String foo = new Date() + ": call";
        System.out.println(foo);
        return foo;
    }

    public String call2() {
        DelayUtil.delay();
        final String foo = new Date() + ": call2";
        System.out.println(foo);
        return foo;
    }

    public String call3(String string) {
        DelayUtil.delay();
        final String foo = new Date() + ": call3" + string;
        System.out.println(foo);
        return foo;
    }

}
