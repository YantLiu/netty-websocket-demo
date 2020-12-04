package com.lyt;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: liuyanting
 * @Description: 1219. 黄金矿工
 * @Date: 2020/12/3
 **/
@Slf4j
public class Solution1219 {
    public static void main(String[] args) {
        int[][] grid = {{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
        System.out.println(getOneMaximumGold(grid));
    }

    public static int getOneMaximumGold(int[][] grid) {
        int lengthI = grid.length;
        int lengthJ = grid[0].length;
        int max = 0;
        for (int i = 0; i < lengthI; i++) {
            for (int j = 0; j < lengthJ; j++) {
                max = Math.max(dfs(grid,i,j), max);
            }
        }
        return max;
    }

    public static int dfs(int[][] grid, int i, int j) {
        int lengthI = grid.length;
        int lengthJ = grid[0].length;
        if (grid[i][j] == 0) {
            return 0;
        }
        int temp = grid[i][j];
        grid[i][j] = 0;

        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;
        if (j > 0 && grid[i][j - 1] != 0) {
            up = dfs(grid, i, j - 1);
        }
        if (j < lengthJ - 1 && grid[i][j + 1] != 0) {
            down = dfs(grid, i, j + 1);
        }
        if (i > 0 && grid[i - 1][j] != 0) {
            left = dfs(grid, i - 1, j);
        }
        if (i < lengthI - 1 && grid[i + 1][j] != 0) {
            right = dfs(grid, i + 1, j);
        }
        int max = Math.max(Math.max(Math.max(up, down), left), right);
        grid[i][j] = temp;
        return temp + max;
    }
}
