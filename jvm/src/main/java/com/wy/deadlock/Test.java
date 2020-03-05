package com.wy.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 死锁
 * @author Dwen
 * @date 2020-01-15 10:14
 */
public class Test {
    private static volatile Object lockA = new Object();
    private static volatile Object lockB = new Object();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.execute(() -> {
            synchronized (lockA){
                System.out.println("1 lockA success");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB){
                    System.out.println("1 lockB success");
                }
            }
        });

        service.execute(() -> {
            synchronized (lockB){
                System.out.println("2 lockB success");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA){
                    System.out.println("2 lockA success");
                }
            }
        });
    }
}
