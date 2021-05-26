package com.lyt.快慢指针遍历;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static ListNode create(int[] arr){
        if (arr == null){
            return  null;
        }
        ListNode listNode = new ListNode(arr[0]);
        ListNode head = listNode;
        for (int i = 1; i < arr.length; i++) {
            listNode.next = new ListNode(arr[i]);
            listNode = listNode.next;
        }
        return head;
    }

    public void print(){
        StringBuilder sb = new StringBuilder();
        sb.append(val);
        ListNode curr = next;
        while (curr != null){
            sb.append(curr.val);
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }
}
