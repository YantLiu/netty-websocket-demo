package com.lyt;

/**
 * @Author: liuyanting
 * @Description: 1247. 交换字符使得字符串相同
 * @Date: 2020-12-8
 **/
public class Solution1641 {
    public static void main(String[] args) {
        System.out.println(minimumSwap("xxyyxyxyxx", "xyyxyxxxyx"));
    }

    public static int minimumSwap(String s1, String s2) {
        if (s1 == null || s1 == "" || s1.equals(s2)) {
            return 0;
        }
        int length = s1.length();
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int dx = 0;
        int dy = 0;
        for (int i = 0; i < length; i++) {
            if (arr1[i] != arr2[i]) {
                if (arr1[i] == 'x') {
                    dx++;
                } else {
                    dy++;
                }
            }
        }
        if (dx % 2 != dy % 2) {
            return -1;
        }
        return dx % 2 == 0 ? (dx + dy) / 2 : (dx + dy) / 2 + 1;
    }
}
