package com.lyt.链表操作;

/**
 * @Author: liuyanting
 * @Description: 61. 旋转链表
 * @Date: 2021-04-29
 **/
public class Q61 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int k = 2;
        ListNode head = ListNode.create(arr);
        rotateRight(head, 2).print();
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null){
            return head;
        }
        ListNode cap = new ListNode(0);
        cap.next = head;
        //计算链表长度
        int length = 0;
        ListNode curr = head;
        ListNode last = null;
        while(curr != null){
            length++;
            if (curr.next == null){
                last = curr;
            }
            curr = curr.next;
        }
        //实际右移位数
        k = k % length;
        //右移截断点
        curr = cap;
        for (int i = 0, n = length - k; i < n; i++) {
            curr = curr.next;
        }
        last.next = cap.next;
        cap.next = curr.next;
        curr.next = null;
        return cap.next;
    }
}
