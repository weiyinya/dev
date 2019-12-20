package com.wy.jvm.init.initObj;

/**
 * 子类
 * @Author wy
 * @Date 2018/12/15
 */
public class Sub extends Super {
    static {
        System.out.println("Sub class init!");
    }
}
