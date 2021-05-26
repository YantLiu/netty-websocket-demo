package com.lyt.字符串操作;

/**
 * @Author: liuyanting
 * @Description: 14. 最长公共前缀
 * @Date: 2021-05-08
 **/
public class Q14 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        System.out.println(new Q14().longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0] != null ? strs[0] : "";
        }
        StringBuilder sb = new StringBuilder();
        int length = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            length = Math.min(length, strs[i].length());
        }
        OK:
        for (int i = 0; i < length; i++) {
            char c =  strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (c != strs[j].charAt(i)){
                    break OK;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
