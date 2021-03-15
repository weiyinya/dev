package com.wy.algorithm.recall;

/**
 * 0-1背包（回溯法）
 *  已知：
 *      现有一个背包，总承重W kg。
 *      现有i个物品，每个物品重 i(n)kg，且每个物品不可分割
 *  求解：
 *      在不超重的情况下背包所能容纳的最大重量物品。
 *
 * @author Dwen
 * @date 2021-03-15 17:03
 */
public class Knapsack01 {

    /**
     * 最大承受重量
     */
    private int maxW;

    /**
     * 物品
     *  下标值代表物品，items[i]代表物品的重量
     */
    private int[] items;

    /**
     * 物品总数量
     */
    private int itemNum;

    public Knapsack01(int maxW, int[] items) {
        this.maxW = maxW;
        this.items = items;
        this.itemNum = items.length;
    }

    /**
     * 计算最大承重（递归法）
     *  如果没有‘枝剪’处理的话 那么递归树是高度为n的完全二叉树
     * @return
     */
    public int countMaxW() {
        if (maxW<=0) return 0;
        return countItem01(0, 0);
    }

    /**
     * 放置物品
     * @param itemIndex 当前要放置的物品
     * @param countMaxW 上一个物品放置后的重量
     * @return
     */
    private int countItem01(int itemIndex, int countMaxW) {

        //物品已经放置完毕
        if (itemIndex>=itemNum) return countMaxW;

        /**
         * 0 不放
         */
        int countItem0 = this.countItem01(itemIndex+1, countMaxW);

        /**
         * 1 放
         */
        //如果放置后超重，则终止
        int countItem1 = 0;
        int localMaxW = countMaxW + items[itemIndex];
        if (localMaxW>maxW) {
            //放置后超重（进行枝剪处理，后续不再计算）
            countItem1 = countMaxW;
        } else {
            //放置后未超重，则进行下一个物品的放置判断
            countItem1 = this.countItem01(itemIndex+1, localMaxW);
        }

        /**
         * 比较放与不放的结果，取最重的那个
         */
        if (countItem0>countItem1) return countItem0;
        return countItem1;
    }

    public static void main(String[] args) {
        int[] items = new int[]{1, 2, 3, 9, 7};
        Knapsack01 knapsack01 = new Knapsack01(23, items);
        System.out.println(knapsack01.countMaxW());
    }
}
