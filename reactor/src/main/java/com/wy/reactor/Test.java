package com.wy.reactor;

import reactor.core.publisher.Flux;

/**
 * @author weiyin
 * @date 2019-12-18 15:23
 */
public class Test {
    public static void main(String[] args) {
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(1));
    }
}
