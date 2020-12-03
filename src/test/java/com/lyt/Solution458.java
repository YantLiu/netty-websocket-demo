package com.lyt;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: liuyanting
 * @Description: 458. 可怜的小猪
 * @Date: 2020/12/3
 **/
@Slf4j
public class Solution458 {
    public static void main(String[] args) {
        System.out.println(poorPigs(1000, 15, 60));
    }

    public static int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int n = minutesToTest / minutesToDie + 1;
        for (int i = 1; i < buckets; i++) {
            int b = 1;
            for (int j = 0; j < i; j++) {
                b *= n;
            }
            if (b >= buckets) {
                return i;
            }
        }
        return 0;
    }
}
