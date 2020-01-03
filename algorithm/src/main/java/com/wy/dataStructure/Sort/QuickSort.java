package com.wy.dataStructure.Sort;

import com.wy.util.CommonUtil;

/**
 * 快速排序
 *
 * 递归公式：sort(0, n) = sort(0, i-1) + sort(i+1, n)， 其中 i = partition(0, n)
 *
 * @author weiyin
 * @date 2020-01-03 09:27
 */
public class QuickSort {

    public static void sort(int[] target){
        sort(target, 0, target.length-1);
    }

    private static void sort(int[] target, int startIndex, int endIndex){

        //终止条件
         if (startIndex>=endIndex) return;

        //获取分区点
        int pivot = partition(target, startIndex, endIndex);

        //左右排序
        sort(target, startIndex, pivot-1);
        sort(target, pivot+1, endIndex);
    }

    /**
     * 分区函数
     *  合理的分区函数可以避免分区不合理的现象。这里的分区点就取数组的最后一个元素
     * @param target
     * @param startIndex
     * @param endIndex
     * @return 返回用来分区的下标值
     */
    private static int partition(int[] target, int startIndex, int endIndex){

        //[startIndex, i] 为已排序区，且都是比分区点数值小的（或者大）
        //[j, endIndex] 为未排序区
        int i = startIndex, j = startIndex;

        //选择分区点
        int pivot = target[endIndex];

        while (j<=endIndex){
            if (target[j]<=pivot){
                CommonUtil.swap(target, i++, j++);
            } else {
                j++;
            }
        }

        return i-1;
    }
}
