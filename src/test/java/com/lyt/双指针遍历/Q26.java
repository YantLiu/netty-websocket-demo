package com.lyt.双指针遍历;

import java.util.Arrays;

/**
 * @Author: liuyanting
 * @Description: 26. 删除有序数组中的重复项
 * @Date: 2021-04-30
 **/
public class Q26 {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int n = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[n]) {
                n++;
                nums[n] = nums[i];
            }
        }
        return n + 1;
    }
}
