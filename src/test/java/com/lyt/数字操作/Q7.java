package com.lyt.数字操作;

/**
 * @Author: liuyanting
 * @Description: 7. 整数反转
 * @Date: 2021-05-08
 **/
public class Q7 {
    public static void main(String[] args) {
        System.out.println(new Q7().reverse(-2147483648));
    }

    public int reverse(int x) {
        long curr = x > 0 ? (long) x : -(long) x;
        long result = curr % 10;
        curr = curr / 10;
        while (curr > 0) {
            result = result * 10 + curr % 10;
            curr = curr / 10;
        }
        result = x > 0 ? result : -result;

        if (result < - (2 << 30) || result > (2 << 30) - 1) {
            return 0;
        }
        return (int) result;
    }
}
