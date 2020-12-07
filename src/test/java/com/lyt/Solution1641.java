package com.lyt;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuyanting
 * @Description: 1641. 统计字典序元音字符串的数目
 * @Date: 2020-12-7
 **/
public class Solution1641 {
    public static void main(String[] args) {
        System.out.println(countVowelStrings2(3));
        System.out.println(countVowelStrings2(3));
    }

    public static int countVowelStrings(int n) {
        if (n == 0) {
            return 0;
        }
        List<Integer> list = new ArrayList<>(1024);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        if (n == 1) {
            return 5;
        }
        for (int i = 1; i < n; i++) {
            int len = list.size();
            for (int j = 0; j < len; j++) {
                int m = list.get(j);
                for (int k = 0; k < m - 1; k++) {
                    list.add(k + 1);
                }
            }
        }
        return list.size();
    }

    public static int countVowelStrings2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 5;
        }
        int[] arr = {1, 1, 1, 1, 1};
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                int m = arr[j];
                for (int k = 0; k < j; k++) {
                    arr[k] += m;
                }
            }
        }
        return arr[0] + arr[1] + arr[2] + arr[3] + arr[4];
    }
}
