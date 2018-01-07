package com.example.amqp.config;

/**
 * Created by ksb on 2018. 1. 7..
 */
public class DelayUtil {

    public static void delay() {
        try {
            // thread to sleep for 5000 milliseconds
            Thread.sleep(5000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void delay(int milliseconds) {
        try {
            // thread to sleep for 5000 milliseconds
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
