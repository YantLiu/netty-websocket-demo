package com.lyt.Solution;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuyanting
 * @Description: 401. 二进制手表
 * @Date: 2020-10-12
 **/
@Slf4j
public class Solution401 {
    public static void main(String[] args) {
        log.info("{}", readBinaryWatch(5));
    }

    public static List<String> readBinaryWatch(int num) {
        int[] arr = new int[10];
        List<String> list = new ArrayList<>(16);
        select(arr, 0, 0, num, list);
        return list;
    }


    public static void select(int[] arr, int start, int n, int num, List<String> list) {
        if (n == num) {
            int hour = (arr[0] << 3) + (arr[1] << 2) + (arr[2] << 1) + arr[3];
            int min = (arr[4] << 5) + (arr[5] << 4) + (arr[6] << 3) + (arr[7] << 2) + (arr[8] << 1) + arr[9];
            if (hour < 12 && min < 60) {
                list.add(String.format("%d:%02d", hour, min));
            }
            return;
        }
        for (int i = start; i <= 10 - (num - n); i++) {
            arr[i] = 1;
            select(arr, i + 1, n + 1, num, list);
            arr[i] = 0;
        }
    }
}
