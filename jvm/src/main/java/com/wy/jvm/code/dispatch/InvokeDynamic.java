package com.wy.jvm.code.dispatch;

/**
 * 动态语言类型 invokeDynamic 字节码命令
 * @author weiyin
 * @date 2019-04-24 09:42
 */
public class InvokeDynamic {

    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> System.out.println("aaa")
        );

        thread.start();
    }
}
