package com.lyt.链表操作;

import java.util.HashMap;

/**
 * @Author: liuyanting
 * @Description: 138. 复制带随机指针的链表
 * @Date: 2021-04-29
 **/
public class Q138 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null){
            return null;
        }
        Node curr = head;
        Node copy = new Node(curr.val);
        Node cap = new Node(0);
        cap.next = copy;
        HashMap<Node, Node> map = new HashMap<>();
        map.put(curr, copy);

        curr = curr.next;
        while(curr != null){
            Node node = new Node(curr.val);
            map.put(curr, node);
            copy.next = node;
            copy = copy.next;
            curr = curr.next;
        }

        curr = head;
        copy = cap.next;
        while(curr != null){
            copy.random = map.get(curr.random);
            curr = curr.next;
            copy = copy.next;
        }
        return cap.next;
    }
}
