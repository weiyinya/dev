package com.wy.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @see java.util.concurrent.locks.LockSupport
 * @author weiyin
 * @date 2019-09-18 13:54
 */
public class LockSupportT {
    public static void main(String[] args) {

        Thread current = Thread.currentThread();
        Object obj = new Object();
        Object obj2 = new Object();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(current);

            current.interrupt();
        }).start();

        // 一点修改
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(current);

        }).start();

        LockSupport.park(obj);
        System.out.println("aaa");
        LockSupport.park(obj2);
        System.out.println("bbb");
    }
}
