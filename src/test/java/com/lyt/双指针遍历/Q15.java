package com.lyt.双指针遍历;

import java.util.*;

/**
 * @Author: liuyanting
 * @Description: 15. 三数之和
 * @Date: 2021-04-30
 **/
public class Q15 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> ret = new Q15().threeSum(nums);
        System.out.println(1);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums.length < 3) {
            return ret;
        }
        Arrays.sort(nums);
        if (nums[0] > 0) {
            return ret;
        }
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    List<Integer> list = new ArrayList<>(3);
                    ret.add(list);
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[left]);
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return ret;
    }
}
