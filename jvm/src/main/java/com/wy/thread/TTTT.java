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

        String ss = "ocalhost:8081/station/getImage?head=Public\\Upload\\admin\\2020-11-07\\18opcnc55uontwe.jpg";
        ss = ss.replaceAll("\\\\", "/");
        System.out.println(ss);
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
