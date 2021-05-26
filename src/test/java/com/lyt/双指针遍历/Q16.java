package com.lyt.双指针遍历;

import java.util.Arrays;

/**
 * @Author: liuyanting
 * @Description: 16. 最接近的三数之和
 * @Date: 2021-04-30
 **/
public class Q16 {
    public int threeSumClosest(int[] nums, int target) {
        int near = nums[0] + nums[1] + nums[2];
        int min = Math.abs(near - target);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int c = sum - target;
                if (Math.abs(c - target) < min) {
                    min = Math.abs(c - target);
                    near = sum;
                }
                if (c == 0) {
                    return target;
                } else if (c > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return near;
    }
}
