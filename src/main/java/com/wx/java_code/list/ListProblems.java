package com.wx.java_code.list;

import com.wx.java_code.common.ListNode;

/**
 * Created by wx on 20-6-7
 * Description: LeetCode 链表相关问题集合
 * 
 * 面试题02.02 返回倒数第 k 个节点 {@link #kthToLast}
 * 面试题02.03 删除链表中间的节点 {@link #deleteNode}
 * 
 * 2. 两数相加 {@link #addTwoNumbers}
 * 83. 删除排序链表中的重复元素 {@link #deleteDuplicates}
 * 206. 反转链表 {@link #reverseList}
 */
public class ListProblems {

    /**
     * 面试题02.02 返回倒数第 k 个节点
     * @param head 链表头结点
     * @param k 倒数值
     * @return 倒数第k个节点值
     * 与《剑指Offer》面试题22一致
     */
    public static int kthToLast(ListNode<Integer> head, int k) {
        ListNode<Integer> p = head,q = head;
        int i = 0;
        while (q != null) {
            if (i < k) {
                q = q.next;
                i++;
                continue;
            }
            p = p.next;
            q = q.next;
        }
        return p.val;
    }

    /**
     * 面试题02.03 删除链表中间的节点
     * @param node 待删除节点
     */
    public void deleteNode(ListNode<Integer> node) {
        // TODO: 2021/5/9
    }
    
    /**
     * 2. 两数相加
     * @param l1 参数链表
     * @param l2 参数链表
     * @return 和链表
     */
    public ListNode<Integer> addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode<Integer> result = new ListNode<>(0);
        ListNode<Integer> p = l1, q = l2, tmp = result;
        boolean flag = false;
        while (p != null) {
            int order = p.val + q.val;
            int plus = flag ? order + 1 : order;
            flag = false;
            if (plus >= 10) {
                tmp = new ListNode<>(plus - 10);
                flag = true;
            } else {
                tmp = new ListNode<>(plus);
            }
            p = p.next;
            q =q.next;
            tmp = tmp.next;
        }
        while (q != null) {
            if (flag) {
                tmp = new ListNode<>(q.val + 1);
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
     * 83. 删除排序链表中的重复元素
     * @param head 链表头结点
     * @return 去重后的链表头结点
     */
    public ListNode<Integer> deleteDuplicates(ListNode<Integer> head) {
        if (head == null) return null;
        ListNode<Integer> slow = head, fast = head;
        while (fast != null) {
            if (!fast.val.equals(slow.val)) {
                slow = slow.next;
                slow.val = fast.val;
//                slow.next = fast;
//                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }

    /**
     * 206. 反转链表
     * @param head 头结点
     * @return 反转后的头结点
     */
    public ListNode<Integer> reverseList(ListNode<Integer> head) {
        ListNode<Integer> result = null, temp = null;
        while (head != null) {
            result = new ListNode<>(head.val);
            result.next = temp;
            temp = result;
            head = head.next;
        }
        return result;
    }
}
