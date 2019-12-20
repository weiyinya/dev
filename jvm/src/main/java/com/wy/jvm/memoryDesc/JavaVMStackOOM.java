package com.wy.jvm.memoryDesc;

/**
 * 拟机栈和本地方法栈溢出-01
 * VM Args: -Xss1M
 * @Author wy
 * @Date 2018/10/22
 */
public class JavaVMStackOOM {
    private void dontStop(){
        while (true){

        }
    }

    public void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(
                    ()->dontStop()
            );
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
