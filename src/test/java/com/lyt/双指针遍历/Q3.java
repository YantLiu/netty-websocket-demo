package com.lyt.双指针遍历;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: liuyanting
 * @Description: 3. 无重复字符的最长子串
 * @Date: 2021-04-30
 **/
public class Q3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            Character c = s.charAt(right);
            Integer index = map.get(c);
            map.put(c, right);
            if (index != null && index >= left) {
                left = index + 1;
            }
            if (right - left + 1 > max) {
                max = right - left + 1;
            }
            right++;
        }
        return max;
    }
}
