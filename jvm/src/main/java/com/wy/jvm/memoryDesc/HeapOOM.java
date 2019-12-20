package com.wy.jvm.memoryDesc;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存异常
 * VM Args: -Xms20m -Xmx20m
 * @Author wy
 * @Date 2018/10/22
 */
public class HeapOOM {
    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true){
            list.add(new OOMObject());
        }
    }
}
