package com.wy.jvm.memoryDesc;

/**
 * 虚拟机栈和本地方法栈溢出-01
 * VM Args: -Xss256k
 * @Author wy
 * @Date 2018/10/22
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args){
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch(Throwable e){
            System.out.println(oom.stackLength);
            throw e;
        }
    }
}