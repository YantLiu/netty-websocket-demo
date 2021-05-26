package com.lyt.链表操作;

/**
 * @Author: liuyanting
 * @Description: K 个一组翻转链表
 * @Date: 2021-04-28
 **/
public class Q25 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int k = 2;
        ListNode head = ListNode.create(arr);
        reverseKGroup(head, k).print();
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode cap = new ListNode(0);
        cap.next = head;
        ListNode pre = cap;
        ListNode end = cap;

        while(end.next != null){
            end = lastK(pre, k);
            if (end == null){
                break;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
        }
        return cap.next;
    }

    /*
     * 逆转链表
     */
    public static ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode curr = head;
        while(curr != null){
            ListNode next = curr.next;

            curr.next = pre;

            pre = curr;
            curr = next;
        }
        return pre;
    }

    /*
     * 后k节点
     */
    public static ListNode lastK(ListNode listNode, int k){
        for (int i = 0; i < k && listNode != null; i++) {
            listNode = listNode.next;
        }
        return listNode;
    }
}
