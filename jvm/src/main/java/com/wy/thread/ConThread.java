package com.wy.thread;

import com.wy.proxy.jdk.TestHandler;

import java.util.Currency;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替打印
 * @author Dwen
 * @date 2020-04-03 00:22
 */
public class ConThread {

    public static volatile Thread currentT = null;

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

        StringBuffer stringBuffer = new StringBuffer();

        Thread threadA = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){

                lock.lock();
                if (lock.tryLock()){ //获取到锁
                    if (currentT!=Thread.currentThread()){//该自己获取锁
                        stringBuffer.append("A").append("\r");
                        currentT = Thread.currentThread();
                    }

                    lock.unlock();
                    condition.signal();
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                } else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                if (lock.tryLock()){ //获取到锁
                    if (currentT!=Thread.currentThread()){//该自己获取锁
                        stringBuffer.append("B").append("\r");
                        currentT = Thread.currentThread();
                    }

                    lock.unlock();
                    condition.signal();
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                } else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
        });

        threadA.start();
        threadB.start();

        TimeUnit.SECONDS.sleep(5);

        threadA.interrupt();
        threadB.interrupt();

        System.out.println(stringBuffer.toString());
    }

}
