package com.wy.jvm.init.initObj;

/**
 * 父类
 * @Author wy
 * @Date 2018/12/15
 */
public class Super {
    public static int value = 10;

    static {
        System.out.println("Super class init!");
    }
}
