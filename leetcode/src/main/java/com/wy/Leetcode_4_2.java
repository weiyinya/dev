package com.wy;

/**
 * 寻找两个正序数组的中位数
 * @author Dwen
 * @date 2020-03-05 16:49
 */
public class Leetcode_4_2 {

    /**
     * 解法二：遍历两个数组，求中位数。
     * 二分查找法
     *  双有序数组求第k/2 | k/2,k/2+1个最小的数  时间复杂度 o(log(m+n))
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = 0, length2 = 0;
        if (nums1!=null) length1 = nums1.length;
        if (nums2!=null) length2 = nums2.length;

        long totalLength = length1 + length2;

        if (totalLength==0) {
            return 0;
        } else {
            long divisor = totalLength / 2;
            long remainder = totalLength % 2;
            if (remainder == 0) { //偶数
                int [] subs = getSubValue(nums1, length1, nums2, length2,  divisor - 1, 2);
                return (double) (subs[0] + subs[1]) / 2;
            } else { //奇数
                int [] subs = getSubValue(nums1, length1, nums2, length2, divisor, 1);
                return subs[0];
            }
        }
    }

    /**
     *
     * @param nums1
     * @param nums2
     * @param targetIndex
     * @param subArrayLength
     * @return
     */
    private int[] getSubValue(int[] nums1, int length1, int[] nums2, int length2, long targetIndex, int subArrayLength) {
        int[] subs = new int[subArrayLength];
        int subArrayIndex = 0;

        //数组1当前移动到的下标，和下标值
        int index1 = 0;
        Integer index1Val = 0;
        //数组2当前移动到的下标，和下标值
        int index2 = 0;
        Integer index2Val = 0;

        //两个数组合并后的下标及下标值
        int totalIndex = -1;
        int targetVal = -1;

        while (subArrayIndex<subArrayLength) {
            if (index1<length1) {
                index1Val = nums1[index1];
            } else {
                index1Val = null;
            }

            if (index2<length2) {
                index2Val = nums2[index2];
            } else {
                index2Val = null;
            }

            if (index1Val==null) {
                targetVal = index2Val;
                index2++;
            }
            if (index2Val==null) {
                targetVal = index1Val;
                index1++;
            }

            if (index1Val!=null && index2Val!=null) {
                if (index1Val<=index2Val) {
                    targetVal = index1Val;
                    index1++;
                } else {
                    targetVal = index2Val;
                    index2++;
                }
            }

            totalIndex++;

            if (totalIndex>=targetIndex) {
                subs[subArrayIndex++] = targetVal;
            }
        }

        return subs;
    }


    public static void main(String[] args) {
        Leetcode_4_2 leetcode_4 = new Leetcode_4_2();
        int[] nums1 = new int[] {0,0,0,0,0};
        int[] nums2 = new int[] {-1,0,0,0,0,0,1};
        double result = leetcode_4.findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }
}
