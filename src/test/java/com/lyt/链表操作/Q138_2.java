package com.lyt.链表操作;

import java.util.HashMap;

/**
 * @Author: liuyanting
 * @Description: 138. 复制带随机指针的链表
 * @Date: 2021-04-29
 **/
public class Q138_2 {
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

    HashMap<Node, Node> visiteHash = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null){
            return null;
        }
        if (this.visiteHash.containsKey(head)){
            return this.visiteHash.get(head);
        }
        Node node = new Node(head.val);
        this.visiteHash.put(head, node);
        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);

        return node;
    }
}
