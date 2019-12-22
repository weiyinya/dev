package com.wy.dataStructure.tree.heap;

import java.util.stream.IntStream;

/**
 * 堆
 * 特点：
 * 1. 堆是一个完全二叉树；
 * 2. 堆中每一个节点的值都必须大于等于（或小于等于）其子树中每个节点的值。
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

    public Heap(int size) {
        this.size = size;
        heapArray = new int[size + 1]; //不使用下标0
    }

    public Heap(HeapType type, int size) {
        this.type = type;
        this.size = size;
        heapArray = new int[size + 1]; //不使用下标0
    }

    @Override
    public void insert(int value) {
        if (used >= size) {
            System.out.println("堆已满");
            return;
        }

        int index = used + 1;
        heapArray[index] = value;

        //数组末尾插入，从后至前的判断是否满足堆定义，不满足则父子交互，只到index=1为止
        while (index / 2 != 0) {
            if (value > heapArray[index / 2]) {
                swap(index, index / 2);
            } else {
                break;
            }
            index = index / 2;
        }
        used++;
    }

    /**
     * 堆父子元素交换交换
     *
     * @params 两个需要交换的下标
     */
    private void swap(int firstIndex, int secondIndex) {
        int temp = heapArray[firstIndex];
        heapArray[firstIndex] = heapArray[secondIndex];
        heapArray[secondIndex] = temp;
    }

    @Override
    public void dropTop() {
        if (used <= 0) {
            System.out.println("堆内无元素");
            return;
        }
        swap(1, used);

        heapify(used-1, 1);
        used--;
    }

    /**
     * 自上往下堆化
     * @param n 终止的下标
     * @param i 起始的下标
     */
    private void heapify(int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && heapArray[i] < heapArray[i * 2]) maxPos = i * 2;
            if (i * 2 + 1 <= n && heapArray[maxPos] < heapArray[i * 2 + 1]) maxPos = i * 2 + 1;
            if (maxPos == i) break;
            swap(i, maxPos);
            i = maxPos;
        }
    }

    @Override
    public void println() {
        IntStream.of(heapArray).limit(used+1).forEach(System.out::println);
    }

    @Override
    public void buildHeap(int[] eles) {

    }

    public static enum HeapType {
        //大顶堆
        BIG,
        //小顶堆
        SMALL
    }
}
