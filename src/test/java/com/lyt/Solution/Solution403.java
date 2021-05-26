package com.lyt.Solution;

/**
 * @Author: liuyanting
 * @Description: 403. 青蛙过河
 * @Date: 2020-10-12
 **/
public class Solution403 {
    public static void main(String[] args) {
        int[] arr = {0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println(canCross2(arr));
    }

    public static boolean canCross2(int[] stones) {
        int len = stones.length;
        if (stones[1] != 1) {
            return false;
        }
        boolean[][] dp = new boolean[len][len + 1];
        dp[0][1] = true;
        for (int i = 1; i < len; i++) {
            boolean flag = false;
            for (int j = i - 1; j >= 0; j--) {
                int k = stones[i] - stones[j];
                if (k > i) {
                    break;
                }
                if (dp[j][k]) {
                    dp[i][k - 1] = true;
                    dp[i][k] = true;
                    dp[i][k + 1] = true;
                    flag = true;
                }
            }
            if (i == len -1 && !flag) {
                return false;
            }
        }
        return true;
    }

    public static boolean canCross(int[] stones) {
        int len = stones.length;
        if (stones[1] != 1) {
            return false;
        }
        boolean[][] dp = new boolean[len][len + 1];
        dp[0][0] = true;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                int k = stones[i] - stones[j];
                if (k <= j + 1) {
                    dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                    if (i == len - 1 && dp[i][k]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
