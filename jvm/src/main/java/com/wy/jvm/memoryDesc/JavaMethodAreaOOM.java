package com.wy.jvm.memoryDesc;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * 运行时方法区内存溢出(元空间)
 * VM Args: -XX:MetaspaceSize=5M -XX:MaxMetaspaceSize=5M
 * @Author wy
 * @Date 2018/11/4
 */
public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(HeapOOM.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(
                    (MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o, args)
            );
            enhancer.create();
        }
    }
}