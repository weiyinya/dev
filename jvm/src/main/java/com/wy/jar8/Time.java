package com.wy.jar8;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author Dwen
 * @date 2020-04-18 22:06
 */
public class Time {

    public static void main(String[] args){
        test4();
    }

    public static void test1(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
    }

    public static void test2(){
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
    }

    public static void test3(){
        Instant instant = Instant.now();
        System.out.println(instant);

        Date.from(instant);
        new Date().toInstant();
    }

    public static void test4(){
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
    }
}
