package com.wy.dataStructure.tree.heap;

/**
 * 堆
 * 特点：
 *  1. 堆是一个完全二叉树；
 *  2. 堆中每一个节点的值都必须大于等于（或小于等于）其子树中每个节点的值。
 *
 * @author weiyin
 * @date 2019-12-20 10:44
 */
public class Heap implements HeapOperator {

    //默认大顶堆
    private HeapType type = HeapType.BIG;

    //堆大小
    private int size;
    private int[] heapArray;

    //已使用堆大小
    private int used;

    public Heap(int size){
        this.size = size;
        heapArray = new int[size];
    }

    public Heap(HeapType type, int size){
        this.type = type;
        this.size = size;
        heapArray = new int[size];
    }

    @Override
    public void insert(int value) {

    }

    @Override
    public void dropTop(int value) {

    }

    @Override
    public void buildHeap(int[] eles) {

    }

    public static enum HeapType{
        //大顶堆
        BIG,
        //小顶堆
        SMALL
    }
}
