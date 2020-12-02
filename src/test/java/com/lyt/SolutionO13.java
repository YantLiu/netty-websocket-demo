package com.lyt;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuyanting
 * @Description: 剑指 Offer 13. 机器人的运动范围
 * @Date: 2020-10-12
 **/
@Slf4j
public class SolutionO13 {
    public static void main(String[] args) {
        System.out.println(movingCount(1, 2, 1));
    }

    public static int movingCount(int m, int n, int k) {
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        int num = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else {
                    int ki = i / 10 + i % 10 + j / 10 + j % 10;
                    if (ki > k) {
                        dp[i][j] = false;
                    } else if (i == 0 && j > 0) {
                        dp[i][j] = dp[i][j - 1];
                    } else if (j == 0 && i > 0) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                    }
                    if (dp[i][j]) {
                        num++;
                    }
                }
            }
        }
        return num;
    }
}
