package com.lyt.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuyanting
 * @Description: 387. 字符串中的第一个唯一字符
 * @Date: 2021-04-28
 **/
public class Q387 {
    public static int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] cArr = s.toCharArray();
        for (int i = 0; i < cArr.length; i++) {
            Integer num = map.get(cArr[i]);
            if (num == null){
                map.put(cArr[i], 1);
            } else {
                map.put(cArr[i], num+1);
            }
        }
        for (int i = 0; i < cArr.length; i++) {
            if (map.get(cArr[i]) == 1){
                return i;
            }
        }
        return  -1;
    }
}
