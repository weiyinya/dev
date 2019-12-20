package com.wy.jvm.memoryDesc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存溢出
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 * @Author wy
 * @Date 2018/11/4
 */
public class DirectMemoryOOM {

    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true){
            unsafe.allocateMemory(_1M);
        }
    }
}