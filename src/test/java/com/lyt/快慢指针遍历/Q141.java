package com.lyt.快慢指针遍历;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: liuyanting
 * @Description: 141. 环形链表
 * @Date: 2021-05-07
 **/
public class Q141 {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>(256);
        ListNode curr = head;
        while (curr != null) {
            if (set.contains(curr)) {
                return true;
            }
            set.add(curr);
            curr = curr.next;
        }
        return false;
    }


    //快慢指针
    public boolean hasCycle2(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow == fast;
    }
}
