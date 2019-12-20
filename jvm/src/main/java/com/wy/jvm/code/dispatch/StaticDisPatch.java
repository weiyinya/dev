package com.wy.jvm.code.dispatch;

/**
 * 静态分派
 *
 * @author weiyin
 * @date 2019-04-23 15:48
 */
public class StaticDisPatch {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human human) {
        System.out.println("hello human");
    }

    public void sayHello(Man man) {
        System.out.println("hello man");
    }

    public void sayHello(Woman woman) {
        System.out.println("hello woman");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();

        StaticDisPatch staticDisPatch = new StaticDisPatch();
        staticDisPatch.sayHello(man);
        staticDisPatch.sayHello(woman);
    }
}
