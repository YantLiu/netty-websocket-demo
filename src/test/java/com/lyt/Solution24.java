package com.lyt;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuyanting
 * @Description: 24. 两两交换链表中的节点
 * @Date: 2020-10-12
 **/
public class Solution24 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4)
                        )
                )
        );
        head = swapPairs(head);
        System.out.println(head);
    }

    public static ListNode swapPairs(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode first = head.next;
        ListNode current = null;
        while (head != null && head.next != null) {
                //交换节点
                ListNode next = head.next;
                head.next = next.next;
                next.next = head;
                if (current != null) {
                    current.next = next;
                }
                current = head;
                head = head.next;
        }
        return first;
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
