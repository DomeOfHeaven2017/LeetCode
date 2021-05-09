package com.wx.java_code.common;

/**
 * 循环单链表
 */
public class CircleList{
    //头结点
    public ListNode head;
    //尾结点
    public ListNode tail;
    //链表长度
    private int length;

    public boolean insert(ListNode node) {
        if (head == null) {
            //空链表
            head = node;
            tail = node;
        } else {
            node.next = head;
            tail.next = node;
            tail = node;
        }
        length ++;
        return true;
    }

    public ListNode delete(ListNode node) {
        ListNode tmp = head;
        //所找结点为头结点
        if (node.equals(tmp)) {
            head = head.next;
            tail.next = head;
            length -- ;
        } else {
            //遍历找该结点
            while (tmp.next != null) {
                if (node.equals(tmp.next)) {
                    ListNode n = tmp.next.next;
                    tmp.next = n;
                    length --;
                    break;
                }
                tmp = tmp.next;
            }
        }
        return tmp;
    }
}
