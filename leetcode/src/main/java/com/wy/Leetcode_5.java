package com.wy;

/**
 * 最长回文子串
 * @author Dwen
 * @date 2020-03-05 16:49
 */
public class Leetcode_5 {

    /**
     * 中心扩散法
     *
     * @return
     */
    public String longestPalindrome(String s) {
        if (s==null || s.length()==0) return s;
        char[] strs = s.toCharArray();

        int maxIndex = s.length() - 1;

        int localIndex = 0;

        // [resultBeginIndex, localIndex, resultEndIndex]
        int resultBeginIndex = 0;
        int resultEndIndex = 0;
        int maxResultLength = 1;

        int beginIndex = 0;
        int endIndex = 0;
        //遍历数组，依次进行中心扩散

        /**
         * 除了首位和末尾，其他位都可以分别做一次奇数扩散和偶数扩散。
         *  代码里边只要把这个逻辑区分清楚就好了
         */
        while (localIndex<=maxIndex) {

            //边界1-偶数扩散（非首位才可以做偶数扩散）
            if (localIndex!=0) {
                beginIndex = localIndex - 1;
                endIndex = localIndex;

                int time1 = this.moveTimes(strs, maxIndex, beginIndex, endIndex);
                //回文子串长度大于1，则和当前最大回文子串进行比较，取最长的回文子串。
                if (time1*2>maxResultLength) {
                    maxResultLength = time1*2;
                    resultBeginIndex = beginIndex - (time1 - 1);
                    resultEndIndex = endIndex + (time1 - 1);
                }
            }

            //边界2-奇数扩散（非首位且非尾位才可奇数扩散）
            if (localIndex!=0 && localIndex!=maxIndex) {
                beginIndex = localIndex-1;
                endIndex = localIndex+1;
                int time2 = this.moveTimes(strs, maxIndex, beginIndex, endIndex);
                //回文子串长度大于1，则和当前最大回文子串进行比较，取最长的回文子串。
                if (time2*2+1>maxResultLength) {
                    maxResultLength = time2*2+1;
                    resultBeginIndex = localIndex - time2;
                    resultEndIndex = localIndex + time2;
                }
            }

            localIndex++;
        }

        if (resultEndIndex>=maxIndex) return s.substring(resultBeginIndex);

        return s.substring(resultBeginIndex, resultEndIndex+1);
    }

    /**
     * 计算中心扩散次数
     *  注：这里可以直接计算长度，由这个长度值统一计算左右下边值（官方解法）
     * @param strs
     * @param maxIndex
     * @param beginIndex
     * @param endIndex
     * @return
     */
    private int moveTimes(char[] strs, int maxIndex, int beginIndex, int endIndex) {
        int moveTime = 0;

        //边界：已到达两端，不再扩散
        if (beginIndex<0 || endIndex>maxIndex || beginIndex>endIndex) return moveTime;

        while (strs[beginIndex] == strs[endIndex]) {
            moveTime++;
            beginIndex--;
            endIndex++;

            //边界：已到达两端，不再扩散
            if (beginIndex<0 || endIndex>maxIndex) break;
        }

        return moveTime;
    }

    public static void main(String[] args) {
        Leetcode_5 leetcode_5 = new Leetcode_5();
        String ss = "cbbbd";
        System.out.println(leetcode_5.longestPalindrome(ss));
    }
}
