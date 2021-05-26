package com.lyt.快慢指针遍历;

import io.swagger.models.auth.In;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: liuyanting
 * @Description: 202. 快乐数
 * @Date: 2021-05-07
 **/
public class Q202 {
    public static void main(String[] args) {
        System.out.println(new Q202().isHappy(19));
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (!set.contains(n)){
            set.add(n);
            n = calHappy(n);
        }
        return n == 1;
    }

    public int calHappy(int n) {
        int sum = 0;
        while (n > 0) {
            int m = n % 10;
            sum += m * m;
            n = n / 10;
        }
        return sum;
    }


    //快慢指针
    public boolean isHappy2(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = bitSquareSum(slow);
            fast = bitSquareSum(fast);
            fast = bitSquareSum(fast);
        } while (slow != fast);

        return slow == 1;
    }

    private int bitSquareSum(int n) {
        int sum = 0;
        while (n > 0) {
            int bit = n % 10;
            sum += bit * bit;
            n = n / 10;
        }
        return sum;
    }
}
