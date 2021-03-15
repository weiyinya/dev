package com.wy.base;

/**
 * 1、finally并不会影响try catch中的return结果，因为 return结果在执行finally时已经额外保存起来了，
 *         在finally执行完毕后，将之前保存的结果压入当前桢栈。
 * 2、但是如果finally中执行return语句的话，会提前终止方法调用，直接返回finally的return结果
 *
 * @author Dwen
 * @date 2020-03-11 17:11
 */
public class Finally {

    private static final String config = "哈哈哈";

    public static void main(String[] args) {
        Finally f = new Finally();
        System.out.println(f.f());
        System.out.println(config);
    }

    public int f(){
        int a = 0;

        try {
            return a;
        } finally {
             a++;
        }
    }
}
