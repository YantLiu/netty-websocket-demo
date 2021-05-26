package com.lyt.Solution;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuyanting
 * @Description: 78. 子集
 * @Date: 2020-10-12
 **/
@Slf4j
public class Solution78 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        log.info("{}", subsets(arr));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        int len = nums.length;
        List<List<Integer>> ret = new ArrayList<>(len * len);
        for (int i = 0; i <= nums.length; i++) {
            select(nums, 0, 0, i, new boolean[len], ret);
        }
        return ret;
    }

    private static void select(int[] nums, int start, int n, int m, boolean[] flag, List<List<Integer>> ret) {
        int len = nums.length;
        if (n == m) {
            List<Integer> list = new ArrayList<>(len);
            for (int i = 0; i < len; i++) {
                if (flag[i] == true) {
                    list.add(nums[i]);
                }
            }
            ret.add(list);
            return;
        }
        for (int i = start; i <= len - (m - n); i++) {
            flag[i] = true;
            select(nums, i + 1, n + 1, m, flag, ret);
            flag[i] = false;
        }
    }
}
