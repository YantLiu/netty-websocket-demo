package com.lyt.数字操作;

/**
 * @Author: liuyanting
 * @Description: 9. 回文数
 * @Date: 2021-05-08
 **/
public class Q9 {
    public static void main(String[] args) {
        System.out.println(new Q9().isPalindrome(121));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int y = 0;
        int k = x;
        while (k > 0) {
            y = y * 10 + k % 10;
            k = k / 10;
        }
        return x == y;
    }

    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        String str = x + "";
        for (int i = 0, j = str.length() - 1, n = str.length() / 2; i < n; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
