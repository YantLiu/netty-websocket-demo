package com.lyt.链表操作;

/**
 * @Author: liuyanting
 * @Description: 206. 反转链表
 * @Date: 2021-04-29
 **/
public class Q206 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 2;
        ListNode head = ListNode.create(arr);
        reverseList(head).print();
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = pre;

            pre = curr;
            curr = next;
        }
        return pre;
    }
}
