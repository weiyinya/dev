package com.wy.jmh.target;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

/**
 * @author weiyin
 * @date 2019-04-06 15:30
 */
public class Helloworld {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void m(){
        System.out.println("aaa");
    }
}
