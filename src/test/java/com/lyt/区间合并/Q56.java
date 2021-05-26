package com.lyt.区间合并;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: liuyanting
 * @Description: 56. 合并区间
 * @Date: 2021-05-07
 **/
public class Q56 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2) {
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int[][] mergeArr = new int[intervals.length][2];
        mergeArr[0] = new int[]{intervals[0][0], intervals[0][1]};
        int n = 1;
        for (int i = 1; i < intervals.length; i++) {
            for (int j = 0; j < n; j++) {
                int[] inter = intervals[i];
                int[] merge = mergeArr[j];
                if (inter[1] >= merge[0] && inter[1] <= merge[1]) {
                    merge[0] = Math.min(inter[0], merge[0]);
                    break;
                } else if (inter[0] >= merge[0] && inter[0] <= merge[1]) {
                    merge[1] = Math.max(inter[1], merge[1]);
                    break;
                } else if (inter[0] <= merge[0] && inter[1] >= merge[1]) {
                    merge[0] = inter[0];
                    merge[1] = inter[1];
                    break;
                } else if (j == n - 1) {
                    mergeArr[n] = new int[]{inter[0], inter[1]};
                    n++;
                    break;
                }
            }
        }
        return Arrays.copyOf(mergeArr, n);
    }
}
