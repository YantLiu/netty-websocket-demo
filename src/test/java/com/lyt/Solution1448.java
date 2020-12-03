package com.lyt;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuyanting
 * @Description: 剑指 Offer 13. 机器人的运动范围
 * @Date: 2020-10-12
 **/
@Slf4j
public class Solution1448 {
    public static void main(String[] args) {
        TreeNode t = new TreeNode(3,
                new TreeNode(3,
                        new TreeNode(4),
                        new TreeNode(2)),
                null
        );
        System.out.println(goodNodes(t));
    }

    public static int goodNodes(TreeNode root) {
        return find(root, root.val);
    }

    public static int find(TreeNode treeNode, int max) {
        int n = 0;
        if (treeNode == null) {
            return n;
        }
        if (treeNode.val >= max) {
            max = treeNode.val;
            n++;
        }
        n += find(treeNode.left, max);
        n += find(treeNode.right, max);
        return n;
    }
}
