package com.lyt;

/**
 * @Author: liuyanting
 * @Description: 419. 甲板上的战舰
 * @Date: 2020-10-16
 **/
public class Solution419 {
    public static void main(String[] args) {
        String[] arr = {
                "x..x",
                "...x",
                "...x",
                /*
                "x.x.x",
                "x.x.x.",
                ".x.x.",
                "x.x.x.",
                ".x.x.",
                 */
        };
        int length = arr.length;
        char[][] board = new char[length][];
        for (int i = 0; i < length; i++) {
            board[i] = arr[i].toCharArray();
        }
        System.out.println(countBattleships(board));
    }

    public static int countBattleships(char[][] board) {
        int num = 0;
        char x = 'x';
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == x) {
                    if (i > 0 && board[i - 1][j] == x) {
                        continue;
                    }
                    if (j > 0 && board[i][j-1] == x) {
                        continue;
                    }
                    num++;
                }
            }
        }
        return num;
    }
}
