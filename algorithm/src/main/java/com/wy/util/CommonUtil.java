package com.wy.util;

/**
 * @author weiyin
 * @date 2019-12-31 16:03
 */
public class CommonUtil {

    /**
     * 交换
     * @param eles
     * @param firstIndex
     * @param secondIndex
     */
    public static void swap(int[] eles, int firstIndex, int secondIndex) {
        int temp = eles[firstIndex];
        eles[firstIndex] = eles[secondIndex];
        eles[secondIndex] = temp;
    }
}
