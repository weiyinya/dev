package com.wy;

import java.util.LinkedHashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        LinkedHashMap linkedHashMap = new LinkedHashMap(8, 0.75f, true);

        linkedHashMap.put("aaa", "aaa");
        linkedHashMap.put("bbb", "bbb");
        linkedHashMap.put("ccc", "ccc");

        linkedHashMap.put("bbb", "bbb");

        for (Object s : linkedHashMap.values()){
            System.out.println(s);
        }
    }
}
