package com.lyt.数字操作;

/**
 * @Author: liuyanting
 * @Description: 172. 阶乘后的零
 * @Date: 2021-05-10
 **/
public class Q172 {
    public static void main(String[] args) {
        System.out.println(new Q172().trailingZeroes(5));
    }

    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n = n / 5;
        }
        return count;
    }
}
