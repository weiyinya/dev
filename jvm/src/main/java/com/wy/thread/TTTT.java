package com.wy.thread;

import com.wy.jmh.TargetRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Dwen
 * @date 2020-03-14 18:42
 */
public class TTTT {
    private static boolean ifFalse= false;

    public static void main(String[] args) throws IOException {

        if (f1()){
            System.out.println(111);
        } else if (f2()){
            System.out.println(222);
        }
    }

    public static boolean f1(){
        System.out.println("aaa");
        return false;
    }

    public static boolean f2(){
        System.out.println("bbb");
        return true;
    }

}
