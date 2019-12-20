package com.wy.jvm.code;

/**
 * 方法静态解析演示 （类加载期间就会把符号引用解析成直接引用了）
 * javap -verbose StaticResolution.class
 * @author weiyin
 * @date 2019-04-23 15:03
 */
public class StaticResolution {

    public static void sayHello(){
        System.out.println("hello world!");
    }

    public static void main(String[] args) {

        /**
         * 编译时已确定的静态调用字节码 invokestatic。invokespecial 也代表编译时确定的
         */
        sayHello();
    }
}
