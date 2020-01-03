package com.wy.dataStructure.Sort;

/**
 * @author weiyin
 * @date 2020-01-02 11:07
 */
public class TestSort {
    public static void main(String[] args) {
        int[] integers = new int[]{1, 6, 3, 2, 4, 7, 5};
        QuickSort.sort(integers);
        for (int integer : integers) {
            System.out.print(integer + ", ");
        }
    }
}
