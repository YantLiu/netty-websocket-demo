package com.lyt.快慢指针遍历;

/**
 * @Author: liuyanting
 * @Description: 876. 链表的中间结点
 * @Date: 2021-05-07
 **/
public class Q876 {
    public ListNode middleNode(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
