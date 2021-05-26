package com.lyt.链表操作;

/**
 * @Author: liuyanting
 * @Description: 19. 删除链表的倒数第 N 个结点
 * @Date: 2021-04-28
 **/
public class Q19 {
    public static void main(String[] args) {
        int[] arr = {1,2};
        int n = 1;
        ListNode head = ListNode.create(arr);
        ListNode ret = removeNthFromEnd(head, n);
        ret.print();
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null && n == 1){
            return null;
        }
        ListNode curr = head;
        ListNode lastN = head;
        for (int i = 0; i < n; i++) {
            if (lastN == null){
                return head;
            }
            lastN = lastN.next;
        }
        if (lastN == null){//n为链表头
            return curr.next;
        }

        while (lastN.next != null){
            curr = curr.next;
            lastN = lastN.next;
        }
        curr.next = curr.next.next;
        return head;
    }
}
