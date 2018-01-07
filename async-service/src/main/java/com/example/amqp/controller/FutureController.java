package com.example.amqp.controller;

import com.example.amqp.service.FutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by ksb on 2018. 1. 7..
 */
@RestController
public class FutureController {

    @Autowired
    FutureService futureService;

    @RequestMapping(method = RequestMethod.GET, value = "/future1")
    public String future1() {
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> futureService.call("test"));
        CompletableFuture future2 = future1.thenApply(foo -> "then apply sync :" + foo);
        CompletableFuture future3 = future2.thenCompose(foo ->
                CompletableFuture.supplyAsync(() ->  "then compose async :" + foo));
        String result = (String) future3.join();
        System.out.println("result: " + result);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/future2")
    public String future2() {
        //두개의 비동기를 합치고 결과도 비동기로 처리할 경우 thenCombineAsync 사용
        CompletableFuture future = CompletableFuture.supplyAsync(() -> futureService.call("test"))
                .thenCombine(CompletableFuture.supplyAsync(() ->  "then combine async :"),
                                (result1, result2) -> result1 + result2);

        String result = (String) future.join();
        System.out.println("result: " + result);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/future3")
    public String future3() {
        List<String> stringList = Arrays.asList("test1", "test2", "test3");
        final String[] resultArray = {""};

        // Start the clock
        long start = System.currentTimeMillis();

        //thenAccept는 미리 어떻게 소비할지를 지정하기 때문에 결과값은 CompletableFuture<Void>를 반환하기 때문에 thenAccept에 꼭 처리하도록
        CompletableFuture[] futures = stringList.stream()
                .map(string -> CompletableFuture.supplyAsync(() -> futureService.delayCall(string)))
                .map(future -> future.thenAccept(s -> {
                    System.out.println("Computation returned: " + s);
                    resultArray[0] = resultArray[0] + " | " + s;
                }))
                .toArray(future->new CompletableFuture[future]);

        //join은 완료를 기다림
        CompletableFuture.allOf(futures).join();

        String result1 = Arrays.stream(futures)
                .map(CompletableFuture::join)
                .map(String.class::cast)
                .collect(Collectors.joining(", "));

        String result2 = String.join("", resultArray);

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
        System.out.println("result1: " + result1);
        System.out.println("result2: " + result2);

        return "result1: " + result1 + "<br/>" + "result2: " + result2;
    }

}
