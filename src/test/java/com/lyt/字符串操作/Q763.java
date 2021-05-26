package com.lyt.字符串操作;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liuyanting
 * @Description: 763. 划分字母区间
 * @Date: 2021-05-08
 **/
public class Q763 {
    public List<Integer> partitionLabels(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            map.put(S.charAt(i), i);
        }
        int start = 0;
        int last = 0;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            last = Math.max(last, map.get(S.charAt(i)));
            if (last == i) {
                result.add(last - start + 1);
                start = i + 1;
            }
        }
        return result;
    }
}
