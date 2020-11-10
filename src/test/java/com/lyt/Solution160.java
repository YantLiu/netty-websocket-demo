package com.lyt;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuyanting
 * @Description: 160. 相交链表
 * @Date: 2020-10-12
 **/
public class Solution160 {
    public static void main(String[] args) {
        ListNode c = new ListNode(8,
                new ListNode(4,
                        new ListNode(5)
                )
        );
        ListNode listNode1 = new ListNode(4,
                new ListNode(1, c)
        );
        ListNode listNode2 = new ListNode(5,
                new ListNode(0,
                        new ListNode(1, c)
                )
        );

        System.out.println(getIntersectionNode(listNode1, listNode2).val + "");
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode cross = null;
        List<ListNode> listA = ListNode2Array(headA);
        List<ListNode> listB = ListNode2Array(headB);
        if (listA == null || listB == null) {
            return cross;
        }
        int k = listA.size() - listB.size();
        for (int i = listA.size() - 1; i >= 0 && i - k >= 0; i--) {
            if (listA.get(i).equals(listB.get(i - k)) && listA.get(i).val != 0) {
                cross = listA.get(i);
            }
        }
        return cross;
    }

    private static List<ListNode> ListNode2Array(ListNode listNode) {
        if (listNode == null) {
            return null;
        }
        List<ListNode> list = new ArrayList<>();
        list.add(listNode);
        while (listNode.next != null) {
            listNode = listNode.next;
            list.add(listNode);
        }
        return list;
    }

    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
