package com.wy;

import java.util.HashMap;
import java.util.Map;

/**
 * 第三题：给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * @author Dwen
 * @date 2020-03-05 16:49
 */
public class Leetcode_3 {

    /**
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        if (s==null) return 0;

        int length = s.length();
        int i = 0, j = 0;
        int result = 0;

        //滑动窗口 [i, j]
        Map<Character, Integer> window = new HashMap<>();

        for (; j < length; j++){
            if (window.containsKey(s.charAt(j))){
                //当出现重复数据时，i滑动到重复字符下标的后一个位置。（这里有点类似KMP算法的思想，一次滑动多个字符）
                i = Math.max(i, window.get(s.charAt(j)));
            }

            window.put(s.charAt(j), j+1);
            result = j-i+1 > result ? j-i+1 : result;
        }
        return result;
    }

    public static void main(String[] args) {
        Leetcode_3 leetcode3 = new Leetcode_3();
        int len = leetcode3.lengthOfLongestSubstring("");
        System.out.println(len);
    }
}
