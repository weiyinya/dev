package com.wy.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dwen
 * @date 2021-02-24 09:35
 */
public class ConditionT {

    private final static ExecutorService executorService = Executors.newCachedThreadPool();


    public static void main(String[] args) {
        TestObject to = new TestObject();
        executorService.execute(to::third);
        executorService.execute(to::second);
        executorService.execute(to::first);
        executorService.shutdown();
    }

    static class TestObject {
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void first() {
            System.out.println("first is lock");
            lock.lock();
            try {
                System.out.println("first is running");
                condition.signalAll();
            } finally {
                System.out.println("first is unlock");
                lock.unlock();
            }
        }

        public void second() {
            System.out.println("second is lock");
            lock.lock();
            try {
                condition.await();
                System.out.println("second is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("second is unlock");
                lock.unlock();
            }
        }

        public void third() {
            System.out.println("third is lock");
            lock.lock();
            try {
                condition.await();
                System.out.println("third is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("third is unlock");
                lock.unlock();
            }
        }
    }
}
