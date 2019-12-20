package com.wy.proxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * @author weiyin
 * @date 2019-06-04 13:59
 */
public class Test {

    public static final String dynamicClassPath = "/Users/weiyin/Documents/document/java/dynamicInvokeClass/";

    public static void main(String[] args) {

        TestHandler handler = new TestHandler(new TestInterfaceImpl());

        Class[] classes ={TestInterface.class};

        TestInterface instance = (TestInterface) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                classes,
                handler
        );

        instance.test01();

        /**
         * 将类转换成字节码，并输出到文件
         */
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy", new Class[]{instance.getClass()});
        try(
                FileOutputStream fos=new FileOutputStream(new File(dynamicClassPath + "$Proxy.class"))
        ){
            fos.write(bytes);
            fos.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
