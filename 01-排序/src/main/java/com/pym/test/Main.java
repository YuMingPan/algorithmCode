package com.pym.test;

import org.junit.jupiter.api.Test;

public class Main {

    @Test
    public void test() {
    }

    @Test
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode fakeNode = new ListNode(-1);
        fakeNode.next = l1;
        ListNode currL1pre = fakeNode;
        ListNode currL1nex = fakeNode.next;
        ListNode currL2 = l2;

        while (currL2.next != null) {
            if (currL2.val < currL1nex.val) {
                // 先记录currL2的next
                ListNode temp = currL2.next;
                // currL2插入到l1上面
                if (currL1nex == null){
                    currL1pre.next = currL2;
                    currL1pre = currL1pre.next;
                }else{
                    currL2.next = currL1nex;
                    currL1pre.next = currL2;
                    currL2 = temp;
                    currL1pre = currL1pre.next;
                }
            }else{
                currL1pre = currL1pre.next;
                currL1nex = currL1nex.next;
            }
        }
        return l1;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
