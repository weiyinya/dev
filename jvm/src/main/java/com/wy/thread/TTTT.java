package com.wy.thread;

/**
 * @author Dwen
 * @date 2020-03-14 18:42
 */
public class TTTT {
    private static boolean ifFalse= false;

    public static void main(String[] args) {
        new Thread(()->{
            int i =0;

            while (!ifFalse) {
//                   System.out.println(i++);
                i++;
            }
            System.out.println(System.currentTimeMillis());
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ifFalse=true;
        System.out.println(System.currentTimeMillis());
    }

}
