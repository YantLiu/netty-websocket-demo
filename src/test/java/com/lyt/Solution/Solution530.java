package com.lyt.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuyanting
 * @Description: 530. 二叉搜索树的最小绝对差
 * @Date: 2020-10-12
 **/
public class Solution530 {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = null;
        treeNode1.right = treeNode2;
        treeNode2.left = treeNode3;
        System.out.println(getMinimumDifference(treeNode1));
    }


    public static int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>(16);
        go(root, list);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            int n = list.get(i).intValue();
            for (int j = i + 1; j < list.size(); j++) {
                int m = list.get(j).intValue();
                int p = n - m;
                if (p < 0){
                    p = -p;
                }
                if (min - p > 0){
                    min = p;
                }
            }
        }
        return min;
    }

    public static void go(TreeNode root, List<Integer> list) {
        list.add(root.val);
        if (root.left != null) {
            go(root.left, list);
        }
        if (root.right != null) {
            go(root.right, list);
        }
    }

    //Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
