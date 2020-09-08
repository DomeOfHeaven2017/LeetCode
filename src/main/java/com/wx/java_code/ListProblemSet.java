package com.wx.java_code;

import com.wx.java_code.resource.ListNode;

/**
 * Created by wx on 20-6-7
 * Description: LeetCode 链表相关问题集合
 * 2. 两数相加 ${@link #addTwoNumbers}
 * 206. 反转链表 ${@link #reverseList}
 */
public class ListProblemSet {


    /**
     * 2. 两数相加
     * @param l1 参数链表
     * @param l2 参数链表
     * @return 和链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode result = new ListNode(0);
        ListNode p = l1, q = l2, tmp = result;
        boolean flag = false;
        while (p != null) {
            int order = p.val + q.val;
            int plus = flag ? order + 1 : order;
            flag = false;
            if (plus >= 10) {
                tmp = new ListNode(plus - 10);
                flag = true;
            } else {
                tmp = new ListNode(plus);
            }
            p = p.next;
            q =q.next;
            tmp = tmp.next;
        }
        while (q != null) {
            if (flag) {
                tmp = new ListNode(q.val + 1);
                flag = false;
            } else {
                tmp = q;
            }
            q = q.next;
            if (q != null && q.val >= 10) {
                flag = true;
            }
            tmp = tmp.next;
        }
        return result.next;
    }

    /**
     * 206. 反转链表
     * @param head 头结点
     * @return 反转后的头结点
     */
    public ListNode reverseList(ListNode head) {
        ListNode result = null, temp = null;
        while (head != null) {
            result = new ListNode(head.val);
            result.next = temp;
            temp = result;
            head = head.next;
        }
        return result;
    }
}
