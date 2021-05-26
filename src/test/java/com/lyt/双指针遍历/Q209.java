package com.lyt.双指针遍历;

import java.util.Arrays;

/**
 * @Author: liuyanting
 * @Description: 209. 长度最小的子数组
 * @Date: 2021-05-06
 **/
public class Q209 {
    public static void main(String[] args) {
        System.out.println(new Q209().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int min = 0;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= target){
                min = min == 0 ? j - i + 1 : Math.min(min, j - i + 1);
                sum -= nums[i];
                i++;
            }
        }
        return min;
    }
}
