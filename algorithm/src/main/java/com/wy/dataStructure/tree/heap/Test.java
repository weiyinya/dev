package com.wy.dataStructure.tree.heap;

/**
 * @author weiyin
 * @date 2019-12-22 17:37
 */
public class Test {
    public static void main(String[] args) {
        HeapOperator operator = new Heap(10);
        operator.insert(1);
        operator.insert(5);
        operator.insert(10);
        operator.insert(15);
        operator.dropTop();
        operator.dropTop();
        operator.dropTop();
        operator.println();
    }
}
