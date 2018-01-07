package com.example.amqp.controller;

import com.example.amqp.service.AsyncService;
import com.example.amqp.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by ksb on 2018. 1. 6..
 */
@RestController
public class ApiController {

    @Autowired
    private SyncService syncService;

    @Autowired
    private AsyncService asyncService;

    @RequestMapping(method = RequestMethod.GET, value = "/sync")
    public String sync() {
        return this.syncService.call();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sync2")
    public String sync2() {
        // Start the clock
        long start = System.currentTimeMillis();

        String result1 = this.syncService.call2();
        String result2 = this.syncService.call2();

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));

        return result1 + result2;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sync5")
    public List<String> sync5() {
        // Start the clock
        long start = System.currentTimeMillis();

        List<String> stringList = Arrays.asList("test1", "test2", "test3");

        List<String> resultList = stringList.stream()
                .map(syncService::call3)
                .collect(Collectors.toList());

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));

        return resultList;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/async")
    public String async() {
        return this.asyncService.call();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/async2")
    public String async2() {
        // Start the clock
        long start = System.currentTimeMillis();

        CompletableFuture future1 = this.asyncService.call2();
        CompletableFuture future2 = this.asyncService.call3();

        //get은 완료를 기다림
        try {
            System.out.println("result1: " + (System.currentTimeMillis() - start));
            String result1 = (String) future1.get();
            System.out.println("result2: " + (System.currentTimeMillis() - start));
            String result2 = (String) future2.get();

            System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));

            return result1 + result2;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/async3")
    public String async3() {
        // Start the clock
        long start = System.currentTimeMillis();

        CompletableFuture future1 = this.asyncService.call2();
        CompletableFuture future2 = this.asyncService.call3();

        //join은 완료를 기다림
        CompletableFuture.allOf(future1, future2).join();

        try {
            System.out.println("result1: " + (System.currentTimeMillis() - start));
            String result1 = (String) future1.get();
            System.out.println("result2: " + (System.currentTimeMillis() - start));
            String result2 = (String) future2.get();

            System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));

            return result1 + result2;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @RequestMapping(method = RequestMethod.GET, value="/async4")
    public String async4() {
        // Start the clock
        long start = System.currentTimeMillis();
        CompletableFuture future = this.asyncService.call4();

        try {
            String result1 = (String) future.get();
            System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
            return result1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/async5")
    public List<String> async5() {
        // Start the clock
        long start = System.currentTimeMillis();

        List<String> stringList = Arrays.asList("test1", "test2", "test3");
        List<CompletableFuture<String>> futures = stringList.stream()
                .map(string -> CompletableFuture.supplyAsync(() -> syncService.call3(string)))
                .collect(Collectors.toList());

        List<String> resultList = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));

        return resultList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/async6")
    public List<String> async6() {
        List<String> stringList = Arrays.asList("test1", "test2", "test3");

        //커스텀 Executor. 실행할 숫자 결정
        final Executor excetor = Executors.newFixedThreadPool(Math.min(stringList.size(), 100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });

        // Start the clock
        long start = System.currentTimeMillis();

        List<CompletableFuture<String>> futures = stringList.stream()
                .map(string -> CompletableFuture.supplyAsync(() -> syncService.call3(string), excetor))
                .collect(Collectors.toList());

        List<String> resultList = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));

        return resultList;
    }

}
