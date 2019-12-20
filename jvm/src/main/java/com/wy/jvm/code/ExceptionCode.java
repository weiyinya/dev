package com.wy.jvm.code;

/**
 * @Author wy
 * @Date 2018/12/16
 */
public class ExceptionCode {

    private int num = 10;
    private int num2 = 20;

    public void inc() {
        try {
            num++;
            num2 = num + num2;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("error");
        }
    }
}
