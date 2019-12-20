package com.wy.jvm.code;

/**
 * @Author wy
 * @Date 2018/12/22
 */
public class MethodCode {
    static abstract class Human{
    }
    static class Man extends Human{
    }
    static class Woman extends Human{
    }

    public void sayHello(Human human){
        System.out.println("human hello");
    }

    public void sayHello(Man man){
        System.out.println("Man hello");
    }

    public void sayHello(Woman woman){
        System.out.println("Woman hello");
    }


    public static void main(String[] args) {
        Human woman = new Woman();
        Human human = new Man();

        MethodCode methodCode = new MethodCode();
        methodCode.sayHello(woman);
        methodCode.sayHello(human);
    }
}
