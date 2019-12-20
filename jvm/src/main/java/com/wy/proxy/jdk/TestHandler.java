package com.wy.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author weiyin
 * @date 2019-06-04 14:07
 */
public class TestHandler implements InvocationHandler {

    private Object object;

    public TestHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxyName:" + proxy.getClass().getSimpleName());
        System.out.println("targetName:" + object.getClass().getSimpleName());
        return method.invoke(object, args);
    }
}
