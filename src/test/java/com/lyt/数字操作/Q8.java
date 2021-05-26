package com.lyt.数字操作;

/**
 * @Author: liuyanting
 * @Description: 8. 字符串转换整数 (atoi)
 * @Date: 2021-05-08
 **/
public class Q8 {
    public static void main(String[] args) {
        //System.out.println(2 << 30);
        System.out.println(new Q8().myAtoi("-21474836460"));
    }

    public int myAtoi(String s) {
        int max = (2 << 30) - 1;
        int min = 2 << 30;
        long result = 0;
        boolean start = false;//数字开始标记
        boolean symbol = true;//符号位
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!start && c == ' ') {
            } else if (!start && c == '0') {
                start = true;
            } else if (!start && (c == '-' || c == '+')) {
                start = true;
                symbol = c == '+';
            } else if (c < '0' || c > '9') {
                break;
            } else {
                start = true;
                result = result * 10 + (c - '0');
                if (symbol && result > max) {
                    return max;
                }
                if (!symbol && -result < min) {
                    return min;
                }
            }
        }
        result = symbol ? result : -result;
        return (int) result;
    }
}
