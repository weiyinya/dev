package com.wy.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * 循环屏障
 * @author weiyin
 * @date 2019-09-29 10:57
 */
public class CyclicBarrierT {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        IntStream.rangeClosed(1, 3).forEach(idx -> {
            new Thread(()->{
                try {
                    System.out.println("thread:" + idx + ", start");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println("thread:" + idx + ", end");
            }).start();
        });

        System.out.println("Main thread end");
    }
}
