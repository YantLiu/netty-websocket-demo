package com.lyt.链表操作;

/**
 * @Author: liuyanting
 * @Description: 2. 两数相加
 * @Date: 2021-04-28
 **/
public class Q2 {
    public static void main(String[] args) {
        int[] arr = {2,4,3};
        ListNode head = ListNode.create(arr);
        int[] arr2 = {5,6,4};
        ListNode head2 = ListNode.create(arr2);
        addTwoNumbers(head, head2).print();
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cap = new ListNode();
        ListNode addList = cap;
        boolean carryBit = false;//进位标记
        while (l1 != null || l2 != null){
            addList.next = new ListNode();
            addList = addList.next;
            int val = 0;
            //计值
            if (l1 != null){
                val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null){
                val += l2.val;
                l2 = l2.next;
            }
            val +=  carryBit? 1 : 0;
            //移动节点
            if (val > 9) {//进位判断
                carryBit = true;
                addList.val = val - 10;
            } else {
                carryBit = false;
                addList.val = val;
            }
        }
        return cap.next;
    }
}
