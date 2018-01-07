package com.example.amqp.service;

import com.example.amqp.config.DelayUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * Created by ksb on 2018. 1. 6..
 */
@Service
public class AsyncService {

    @Async
    public String call() {
        DelayUtil.delay();
        final String foo = new Date() + ": call";
        System.out.println(foo);
        return foo;
    }

    @Async
    public CompletableFuture<String> call2() {
        DelayUtil.delay();
        final String foo = new Date() + ": call2";
        System.out.println(foo);
        return CompletableFuture.completedFuture(foo);
    }

    @Async
    public CompletableFuture<String> call3() {
        DelayUtil.delay(10000);
        final String foo = new Date() + ": call3";
        System.out.println(foo);
        return CompletableFuture.completedFuture(foo);
    }

    @Async
    public CompletableFuture<String> call4() {
        DelayUtil.delay();
        final String foo = new Date() + ": call4";
        System.out.println(foo);
        return CompletableFuture.supplyAsync(() -> foo);
    }

}
