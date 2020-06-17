package com.wx.java_code;

import com.wx.java_code.resource.ListNode;
import com.wx.java_code.resource.TreeNode;

import java.util.Arrays;
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

    /**
     * 面试题02.03
     * 删除链表中的节点
     * @param node 待删除节点
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }


    /**
     * 面试题04.02
     * 最小高度树
     * @param nums 递增数组
     * @return 树
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length <= 0){
            return null;
        }
        TreeNode root = new TreeNode(nums[nums.length / 2]);
        TreeNode temp = root;
        for (int i = 0 ; i < nums.length ; i ++) {

        }
        return root;
    }

    /**
     * 面试题04.04
     * 检查平衡性
     * @param root 根节点
     * @return 是否平衡
     */
    public boolean isBalanced(TreeNode root) {

    }


    /**
     * 面试题16.01
     * 交换数字
     * @param numbers 源数组
     * @return 交换后的数组
     */
    public static int[] swapNumbers(int[] numbers) {
        int[] result = new int[2];
        int xor = numbers[0] ^ numbers[1];
        result[0] = xor ^ numbers[0];
        result[1] = xor ^ numbers[1];
        return result;
    }

    public static void main(String[] args) {
        //判定字符是否唯一
//        System.out.println(isUnique("leetcode"));
        //16.01
        System.out.println(Arrays.toString(swapNumbers(new int[]{3, 4})));
    }
}
