package com.lyt.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuyanting
 * @Description: 1.两数之和
 * @Date: 2021-04-28
 **/
public class Q1 {

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target){
                     int[] ret = {i, j};
                    return ret;
                }
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])){
                int[] ret = {map.get(target - nums[i]), i};
                return ret;
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }
}
