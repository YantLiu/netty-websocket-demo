package com.lyt.双指针遍历;

/**
 * @Author: liuyanting
 * @Description: 42. 接雨水
 * @Date: 2021-05-06
 **/
public class Q42 {
    public static void main(String[] args) {
        int[] height = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println(trap(height));
    }

    public static int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int sum = 0;
        int left = 1;
        int right = height.length - 2;
        int maxLeft = height[0];
        int maxRight = height[height.length - 1];
        while (left <= right) {
            if (maxLeft <= maxRight) {
                sum += Math.max(0, maxLeft - height[left]);
                maxLeft = Math.max(maxLeft, height[left]);
                left++;
            } else {
                sum += Math.max(0, maxRight - height[right]);
                maxRight = Math.max(maxRight, height[right]);
                right--;
            }
        }
        return sum;
    }
}
