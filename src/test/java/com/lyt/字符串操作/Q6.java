package com.lyt.字符串操作;

/**
 * @Author: liuyanting
 * @Description: 6. Z 字形变换
 * @Date: 2021-05-07
 **/
public class Q6 {
    public String convert(String s, int numRows) {
        if (s == null) {
            return null;
        }
        if (s.length() < numRows || numRows == 1) {
            return s;
        }
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sb[i] = new StringBuilder();
        }
        boolean down = true;//上移下移标记
        for (int i = 0, j = 0, k = s.length(); i < k; i++) {
            sb[j].append(s.charAt(i));
            if (down && j + 1 == numRows) {//下到了最大行
                down = !down;
                j--;
            } else if (!down && j == 0) {//上到了最小行
                down = !down;
                j++;
            } else if (down) {
                j++;
            } else {
                j--;
            }
        }
        for (int i = 1; i < numRows; i++) {
            sb[0].append(sb[i]);
        }
        return sb[0].toString();
    }

    public static void main(String[] args) {
        System.out.println(new Q6().convert2("PAYPALISHIRING", 3));
    }

    public String convert2(String s, int numRows) {
        if (s == null || s.length() < numRows || numRows == 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int k = (numRows - 1) * 2;//周期间距
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < s.length(); ) {
                sb.append(s.charAt(j));
                if (i > 0 && i < numRows - 1) {//中间行
                    int l = j + k - 2 * i;//同一行的对称位置下标
                    if (l < s.length()) {
                        sb.append(s.charAt(j + k - 2 * i));
                    }
                }
                j += k;
            }
        }
        return sb.toString();
    }
}
