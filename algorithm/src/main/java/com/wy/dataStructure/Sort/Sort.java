package com.wy.dataStructure.Sort;

import com.wy.util.CommonUtil;

/**
 * 排序
 * @Author wy
 * @Date 2019/2/2
 */
public class Sort {

    /**
     * 1、插入排序
     *  基本原理：
     *      主要是将数组分为有序区和待排序区。遍历待排序区插入到有序区中
     *  核心操作：
     *      理解i、j下标变动就好
     *      从待排序区取出待排序元素后，放到临时变量中（这时它本来的位置就空了），
     *      倒序遍历排序区，和temp比较，如果大了就向后移动（增序排列），直到temp小于等于已排序元素时，就将temp放入数组的j下标位置
     */
    public static void insertSort(int[] integers) {
        for (int i=1; i<integers.length; i++){
            //当前需要插入的元素
            Integer tem = integers[i];
            //该插入元素的应存在位置
            int j = i;
            for (; j>0 && tem.compareTo(integers[j-1])<0; j--){
                integers[j] = integers[j-1];
            }
            integers[j] = tem;
        }
    }

    /**
     * 2、冒泡排序
     *  代码写法较多，基本原理不变。可：
     *      1、i递增 j->length-i
     *      2、i递减 j->i
     * @param integers
     */
    public static void bubbleSort(int[] integers) {
        int length = integers.length;
        for (int i = length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (integers[j]>integers[j+1]){
                    CommonUtil.swap(integers, j, j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] integers = new int[]{1, 6, 3, 2, 4, 7, 5};
        bubbleSort(integers);
        for (int integer : integers) {
            System.out.print(integer + ", ");
        }
    }
}
