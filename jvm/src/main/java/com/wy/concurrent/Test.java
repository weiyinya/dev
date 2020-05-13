package com.wy.concurrent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * @author weiyin
 * @date 2019-04-29 16:21
 */
public class Test {

    public static int maxV = 0;
    public static int[] array = new int[3];
    public static int[] maxArray;


    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("11", 12312);
        System.out.println(map);
    }

    /**
     *
     * @param times 表示公主
     * @param gifts
     * @param maxValue
     * @return
     */
    public static void select(int times, int[][] gifts,  int maxValue) {

        //i 表示王子
        for (int i=0; i<=2; i++) {
            if (isOk(times, array, i)) {
                array[times] = i;
                maxValue += gifts[i][times];

                if (times==2){
                    if (maxValue>=maxV){
                        maxV = maxValue;
                        maxArray = array;
                    }
                }
                int timess = times + 1;
                select(timess, gifts, maxValue);
            }
        }
    }

    private static boolean isOk(int times, int[] result, int select) {
        boolean isOK = true;
        for (int i=0; i<times; i++){
            if (result[i]==select){
                isOK = false;
                break;
            }
        }
        return isOK;
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
        System.out.println("测试");
        System.out.println("测试2");
    }

}
