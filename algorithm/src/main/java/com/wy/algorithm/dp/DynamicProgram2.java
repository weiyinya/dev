package com.wy.algorithm.dp;

/**
 * 动态规划
 *  求最大递增子序列长度
 *  例：[3,10,8,16,9,34,17,11,32]
 */
public class DynamicProgram2 {

    /**
     * 递归实现（回溯）
     */
    public static class Recursive {

        private int maxLength = Integer.MIN_VALUE;

        private int invokeTimes = 0;
        /**
         * 递归计算最大长度
         * @param a 目标数组
         * @param index 当前遍历进度
         * @param arrayLength 数组长度
         * @param maxLength 当前的递归长度
         * @return
         */
        public void findMaxLength(int[] a, int index, int arrayLength, int maxLength) {

            //更新调用次数
            invokeTimes++;

            //遍历完数组，更新当前最大长度
            if (index>arrayLength-1){
                if (this.maxLength<maxLength) this.maxLength = maxLength;
                return;
            }

            //忽略计算
            findMaxLength(a, index+1, arrayLength, maxLength);
            //不忽略
            if (index==0 || a[index]>a[index-1]){
                findMaxLength(a, index+1, arrayLength, maxLength+1);
            } else {
                findMaxLength(a, index+1, arrayLength, maxLength);
            }

        }
    }

    /**
     * 动态规划-状态转移表实现
     */
    public static class Table {

    }

    /**
     * 动态规划-状态转移方程实现
     */
    public static class Equation {

    }


    public static void main(String[] args) {
        int[] a = new int[]{3,10,8,17,11,32};
        int length = a.length;

        Recursive recursive = new Recursive();
        recursive.findMaxLength(a, 0, length, 0);

        System.out.println("最大长度："+recursive.maxLength);
        System.out.println("调用次数："+recursive.invokeTimes);
    }
}
