package com.wy.jvm.init.initObj;

/**
 * @Author wy
 * @Date 2018/12/15
 */
public class Const {
    static {
        System.out.println("Const class init!");
    }

    public static final String helloworld = "hello world!";
}
