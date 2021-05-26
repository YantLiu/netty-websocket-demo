package com.lyt.数字操作;

/**
 * @Author: liuyanting
 * @Description: 258. 各位相加
 * @Date: 2021-05-10
 **/
public class Q258 {
    public int addDigits(int num) {
        int sum = 0;
        while (num > 9) {
            while (num > 0) {
                sum += num % 10;
                num = num / 10;
            }
            num = sum;
        }
        return num;
    }

    public int addDigits2(int num) {
        if (num == 0) {
            return 0;
        }
        return num % 9 == 0 ? 9 : num % 9;
    }
}
