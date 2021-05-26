package com.lyt.双指针遍历;

/**
 * @Author: liuyanting
 * @Description: 121. 买卖股票的最佳时机
 * @Date: 2021-05-06
 **/
public class Q121 {
    public static void main(String[] args) {
        System.out.println(new Q121().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int min = prices[0];
        int money = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            money = Math.max(money, prices[i] - min);
        }
        return money;
    }
}
