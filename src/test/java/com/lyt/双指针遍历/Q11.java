package com.lyt.双指针遍历;

/**
 * @Author: liuyanting
 * @Description: 11. 盛最多水的容器
 * @Date: 2021-04-30
 **/
public class Q11 {
    public int maxArea(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j; ) {
            int minH = Math.min(height[i], height[j]);
            max = Math.max(max, (j-i) * minH);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }
}
