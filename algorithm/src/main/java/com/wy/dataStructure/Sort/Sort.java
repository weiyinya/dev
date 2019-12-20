package com.wy.dataStructure.Sort;

/**
 * 排序
 * @Author wy
 * @Date 2019/2/2
 */
public class Sort {

    /**
     * 1、插入排序
     */
    public static void insertSort(Integer[] integers) {

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

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{1, 6, 3, 2, 4, 7, 5};
        insertSort(integers);
        for (Integer integer : integers) {
            System.out.print(integer + ", ");
        }
    }
}
