package com.lyt.数字操作;

/**
 * @Author: liuyanting
 * @Description: 43. 字符串相乘
 * @Date: 2021-05-08
 **/
public class Q43 {
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        String res = new String();
        for (int i = num1.length() - 1; i >= 0; i--) {
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < num1.length() - 1 - i; j++) {//结果补0
                temp.append("0");
            }
            int carry = 0;//累加进位
            int a = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0 || carry != 0; j--) {
                int b = j < 0 ? 0 : num2.charAt(j) - '0';
                temp.append((a * b + carry) % 10);
                carry = (a * b + carry) / 10;
            }
            //累加
            res = this.add(res, temp.reverse().toString());
        }
        return res;
    }

    public String add(String num1, String num2) {
        StringBuilder tem = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry != 0; i--, j--) {
            int a = i < 0 ? 0 : num1.charAt(i) - '0';
            int b = j < 0 ? 0 : num2.charAt(j) - '0';
            tem.append((a + b + carry) % 10);
            carry = (a + b + carry) / 10;
        }
        return tem.reverse().toString();
    }
}
