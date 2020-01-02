package com.wy.dataStructure.Sort;

/**
 * 归并排序
 *  一种自下而上的排序方式
 *
 *  递归公式：f(0, n) = merge(f(0, n/2), f(n/2+1, n))
 *  时间复杂度：O(n*log(n)) 可用递归树法计算，满二叉树的每一层时间复杂度都是n，树高log(n)。共计：n * log(n)
 *  空间复杂度：就是merge函数的tmp临时数组，最大的时候tmp为n。即空间复杂度为O(n)
 *
 * @author weiyin
 * @date 2020-01-02 09:43
 */
public class MergeSort {

    public static void sort(int[] target){
        f(target, 0, target.length-1);
    }

    /**
     * f(0, n)函数
     */
    private static void f(int[] target, int startIndex, int endIndex){
        if (startIndex>=endIndex){
            return;
        }
        int median = (endIndex+startIndex)/2;
        //先排序左边的
        f(target, startIndex, median);
        //再排序右边的
        f(target, median+1, endIndex);
        //最后合并
        merge(target, startIndex, endIndex);
    }

    /**
     * merge函数
     */
    private static void merge(int[] target, int startIndex, int endIndex) {

        int[] tmp = new int[endIndex-startIndex+1];

        int median = (endIndex+startIndex)/2;

        int index = 0; //临时数组的下标
        int aIndex = startIndex; //左边数组的下标
        int bIndex = median+1; //右边数组的下标

        //挪移，先挪空一个
        while (aIndex<=median && bIndex<=endIndex){
            //就是挪小的，相等的话挪左边的
            if (target[aIndex]<=target[bIndex]){
                tmp[index++] = target[aIndex++];
            } else {
                tmp[index++] = target[bIndex++];
            }
        }
        //然后挪空剩余的那个
        int tmpEndIndex = tmp.length - 1;
        int surplus = aIndex>median ? bIndex : aIndex; //取还未挪空的下标 a还是b
        while (index<=tmpEndIndex){
            tmp[index++] = target[surplus++];
        }

        //将已排序好的tmp放入target
        int times = tmp.length;
        index=0;
        int targetIndex = startIndex;
        for (int i = 0; i < times; i++) { //从startIndex挪移times次
            target[targetIndex++] = tmp[index++];
        }
    }

}

