package com.lyt.Solution;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Author: liuyanting
 * @Description: 1209. 删除字符串中的所有相邻重复项 II
 * @Date: 2020-10-12
 **/
@Slf4j
public class Solution1209 {
    public static void main(String[] args) {
        System.out.println(removeDuplicates("deeedbbcccbdaa", 3));
    }

    public static String removeDuplicates(String s, int k) {
        if (s == null || "".equals(s) || k > 10000) {
            return s;
        }
        if (k == 0 || k > s.length()) {
            return s;
        }
        if (k == 1) {
            return "";
        }

        char[] carr = s.toCharArray();
        int len = carr.length;
        Character[] arr = new Character[len];
        for (int i = 0; i < len; i++) {
            arr[i] = carr[i];
        }

        Character last = null;
        int n = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] != null) {
                if (last == null) {
                    last = arr[i];
                    n =  1;
                } else if (last.equals(arr[i])) {
                    n++;
                    if (n == k) {
                        //向前清空k个非空字符
                        for (int j = 0; j < k; ) {
                            if (arr[i] != null) {
                                arr[i] = null;
                                j++;
                            }
                            i--;
                        }
                        i = i < 0 ? 0 : i;
                        //回退k个字符
                        for (int j = 0; j < k; ) {
                            if (i == 0) {
                                break;
                            }
                            if (arr[i] != null) {
                                j++;
                            }
                            i--;
                        }
                        last = arr[i];
                        n = 1;
                    }
                } else {
                    last = arr[i];
                    n = 1;
                }
            }
        }
        StringBuilder sb = new StringBuilder(len);
        for (Character c : arr) {
            if (c != null) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
