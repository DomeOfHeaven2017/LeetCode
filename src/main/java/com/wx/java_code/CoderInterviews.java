package com.wx.java_code;

import com.wx.java_code.resource.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desc 程序员面试宝典题目代码
 * @Author wx
 * @Date 2020-3-6
 **/
public class CoderInterviews {


    /**
     * 01.01
     * 判定字符是否唯一
     * @param astr 所给字符串
     * @return 字符串中的字符是否唯一
     */
    public static boolean isUnique(String astr) {
        if (astr == null) {
            return false;
        }
        Map<Character,Integer> map = new HashMap<>(astr.length());
        char[] chars = astr.toCharArray();
        for (char c : chars) {
            if (map.containsKey(c)) {
                return false;
            } else {
                map.put(c, 1);
            }
        }
        return true;
    }

    /**
     * 面试题02.02
     * 返回倒数第 k 个节点
     * @param head 链表头结点
     * @param k 倒数值
     * @return 倒数第k个节点值
     * 与《剑指Offer》面试题22一致
     */
    public static int kthToLast(ListNode head, int k) {
        ListNode p = head,q = head;
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

    public static void main(String[] args) {
        //判定字符是否唯一
        System.out.println(isUnique("leetcode"));
    }
}
