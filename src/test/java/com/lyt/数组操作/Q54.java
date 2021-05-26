package com.lyt.数组操作;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuyanting
 * @Description: 54. 螺旋矩阵
 * @Date: 2021-05-10
 **/
public class Q54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = 0;
        int k = 0;
        res.add(matrix[0][0]);
        while (true) {
            if (k % 4 == 0) {//向右
                int max = m - 1 - k / 4;
                while (i < max) {
                    i++;
                    res.add(matrix[i][j]);
                }
            } else if (k % 4 == 1) {//向下
                int max = n - 1 - k / 4;
                while (j < max) {
                    j++;
                    res.add(matrix[i][j]);
                }
            } else if (k % 4 == 2){
                int min = k/4;
            }
            k++;
        }
    }
}
