package com.wy.proxy.jdk;

/**
 * @author weiyin
 * @date 2019-06-04 14:25
 */
public class TestInterfaceImpl implements TestInterface {
    @Override
    public void test01() {
        System.out.println("test01");
    }

    @Override
    public String test02() {
        System.out.println("test02");
        return "";
    }
}
