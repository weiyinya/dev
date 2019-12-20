package com.wy.jvm.gc;

/**
 * -XX:+PrintGCDetails
 * 垃圾回收日志
 * @Author wy
 * @Date 2018/12/9
 */
public class Gc {

    public static Integer _1M = 1024 * 1024;

    public static void main(String[] args) {
        byte[] by = new byte[2 * _1M];
        System.gc();
    }
}
