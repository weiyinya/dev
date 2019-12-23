package com.wy.dataStructure.tree.heap;

import java.util.stream.IntStream;

/**
 * @author weiyin
 * @date 2019-12-22 17:37
 */
public class Test {
    public static void main(String[] args) {
        int[] eles = new int[]{0, 14, 7, 30, 2, 15, 17};
        Heap.sorted(eles);
        IntStream.of(eles).forEach(System.out::println);
        System.out.println("提交测试");
    }
}
