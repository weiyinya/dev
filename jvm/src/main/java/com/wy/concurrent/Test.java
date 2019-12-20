package com.wy.concurrent;

import java.util.concurrent.*;

/**
 * @author weiyin
 * @date 2019-04-29 16:21
 */
public class Test {

    public static void main(String[] args) {

    }

    /**
     * Exchange测试
     * @throws InterruptedException
     */
    @org.junit.jupiter.api.Test
    public void exchangeTest() throws InterruptedException, ExecutionException {
        Exchanger<String> exchanger = new Exchanger<>();
        ExecutorService pool = Executors.newFixedThreadPool(1);

        Future<String> result = pool.submit(
                () -> {
                    String A = "银行流水A";// A录入银行流水数据
                    String b = "";
                    try {
                        Thread.sleep(4000);
                        b = exchanger.exchange(A);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return b;
                }
        );
        pool.shutdown();
        String B = result.get();

        String A = exchanger.exchange("银行流水B"); //得到A值
        System.out.println("A和B数据是否一致：" + A.equals(B) + "，A录入的是：" + A + "，B录入是：" + B);
    }

    /**
     * 信号量测试
     */
    public void semaphoreTest() {

        Semaphore semaphore = new Semaphore(2);
        System.out.println("aaa");

    }

}
