package com.wy.jmh;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * 基准测试
 * @author weiyin
 * @date 2019-04-06 15:31
 */
public class TargetRunner {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include("Helloworld")
                .exclude("Pref")
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .build();

        new Runner(options).run();
    }
}
