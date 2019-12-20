package com.wy.jvm.code.dispatch;

/**
 * 动态分派
 * @Author wy
 * @Date 2018/12/22
 */
public class DynamicDispatch {
    static abstract class Human{
        protected abstract void sayHello();
    }
    static class Man extends Human{
        @Override
        protected void sayHello() {
            System.out.println("Man hello");
        }
    }
    static class Woman extends Human{
        @Override
        protected void sayHello() {
            System.out.println("Woman hello");
        }
    }

    public static void main(String[] args) {
        Human human = new Man();
        Woman woman = new Woman();
        human.sayHello();
        woman.sayHello();
        human = new Woman();
        human.sayHello();
    }
}
