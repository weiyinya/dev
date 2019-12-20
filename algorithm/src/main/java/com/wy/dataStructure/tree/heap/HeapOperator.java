package com.wy.dataStructure.tree.heap;

/**
 * 堆操作
 * @author weiyin
 * @date 2019-12-20 10:34
 */
public interface HeapOperator {
    //插入数据
    void insert(int value);
    //删除堆顶数据
    void dropTop(int value);
    //建堆
    void buildHeap(int[] eles);
}
