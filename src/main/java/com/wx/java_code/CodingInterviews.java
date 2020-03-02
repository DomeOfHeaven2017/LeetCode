package com.wx.java_code;

import com.wx.java_code.resource.ListNode;
import com.wx.java_code.resource.TreeNode;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * 剑指Offer习题解答
 */
public class CodingInterviews {


    /**
     * 面试题03
     * 数组中重复的数字
     * @param nums 所给数组
     * @return 重复数字
     */
    public int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length < 2 || nums.length > 100000) {
            return 0;
        }
//        int[] temp = new int[nums.length];
//        for (int i = 0 ; i < nums.length ; i++) {
//            temp[nums[i]] ++;
//            if (temp[nums[i]] > 1) {
//                return nums[i];
//            }
//        }
//        return 0;
        int temp = 0;
        for (int i = 0 ; i < nums.length ; i ++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                } else {
                    temp = nums[i];
                    nums[i] = nums[temp];
                    nums[temp] = temp;
                }
            }
        }
        return 0;
    }

    /**
     * 面试题05
     * 替换空格
     * @param s 原始字符串
     * @return 结果字符串
     */
    public static String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder(s.length() * 3);
        for (char c : s.toCharArray()) {
            if (c == ' '){
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
//        return s.replaceAll(" ", "%20");
    }

    /**
     * 面试题06
     * 从尾到头打印链表
     * @param head 链表头结点
     * @return 打印结果数组
     */
    public int[] reversePrint(ListNode head) {
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count ++;
            temp = temp.next;
        }
        int[] result = new int[count];
        while (head != null) {
            result[count - 1] = head.val;
            count -- ;
            head = head.next;
        }
        return result;
    }

    /**
     * 面试题15
     * 二进制中1的个数
     * @param n 所给二进制数
     * @return 1的个数
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count ++;
        }
        return count;
    }

    /**
     * 面试题17
     * 打印从1到最大的n位数
     * @param n 最大数为 10^2 - 1
     * @return 结果数组
     */
    public static int[] printNumbers(int n) {
        int size = (int) Math.pow(10, n) - 1;
        int[] result = new int[size];
        for (int i = 0 ;i < size ; i++){
            result[i] = i + 1;
        }
        System.out.println("size = "+size);
        return result;
    }

    /**
     * 面试题22
     * 链表中倒数第k个节点
     * @param head 链表头节点
     * @param k
     * @return 倒数第k个节点
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        //两次遍历
//        if (head == null) {
//            return null;
//        }
//        ListNode temp = head;
//        int size = 1;
//        while (temp.next != null) {
//            temp = temp.next;
//            size++;
//        }
//        int position = size - k + 1;
//        while (position > 1) {
//            head = head.next;
//            position --;
//        }
//        return head;
        //一次遍历，两个指针
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
        return p;
    }

    /**
     * 面试题24
     * 反转链表
     * @param head 链表头结点
     * @return 反转后的链表头节点
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

    /**
     * 面试题25
     * 合并两个排序的链表
     * @param l1 递增链表1头结点
     * @param l2 递增链表2头结点
     * @return 合并后的链表头结点
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head,temp;
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else {
            if (l1.val < l2.val) {
                temp = l1;
                l1 = l1.next;
            } else {
                temp = l2;
                l2 = l2.next;
            }
        }
        head = temp;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        if (l1 == null) {
            temp.next = l2;
        } else if (l2 == null) {
            temp.next = l1;
        }
        return head;
    }

    /**
     * 面试题27
     * 二叉树的镜像
     * @param root 根节点
     * @return 镜像二叉树根节点
     */
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

    /**
     * 面试题55 - I
     * 二叉树的深度
     * @param root 二叉树根节点
     * @return 深度
     */
    public static int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 面试题56-I
     * 数组中数字出现的次数 I
     * @param nums 所给数组
     * @return 单次次数出现的数字集合
     */
    public static int[] singleNumbers(int[] nums) {
        if (nums == null || nums.length <= 2 || nums.length >= 10000) {
            return nums;
        }
        int xor = 0;
        for (int i : nums) {
            xor ^= i;
        }
        int index = xor & (-xor);
        int x = 0;
        for (int i : nums) {
            if ((index & i) != 0) {
                x ^= i;
            }
        }
        return new int[]{x, x ^ xor};
    }

    /**
     * 面试题56-II
     * 数组中数字出现的次数 II
     * @param nums 所给数组
     * @return 单次次数出现的数字集合
     */
    public static int singleNumber(int[] nums) {
        if (nums == null || nums.length < 1 || nums.length > 10000){
            return 0;
        }
        HashMap<Integer,Integer> map = new HashMap<>((nums.length + 2) / 3);
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, 2);
            } else {
                map.put(i, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getValue();
            }
        }
        return 0;
    }

    /**
     * 面试题58 - II
     * 左旋转字符串
     * @param s 字符串
     * @param n 旋转位置
     * @return 旋转结果
     */
    public static String reverseLeftWords(String s, int n) {
        if (s == null || s.length() < 1 || n < 1 || n > 10000) {
            return "";
        }
        return s.substring(n) + s.substring(0, n);
    }

    /**
     * 面试题64
     * 求1+2+...+n
     * @param n 最大求值
     * @return n阶和
     */
    public static int sumNums(int n) {
        int sum = n;
        boolean is = (n > 0) && ((sum = sum + sumNums(n - 1)) > 0);
        return sum;
    }


    public static void main(String[] args) {

        //打印从1到最大的n位数
//        System.out.println(printNumbers(2));
        //左旋转字符串
//        System.out.println(reverseLeftWords("abcdefg", 2));
        //替换空格
//        System.out.println(replaceSpace("We are happy."));
        //二叉树深度
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);
//        System.out.println("Depth = "+maxDepth(root));
        //二叉树的镜像
//        TreeNode mirror = mirrorTree(root);
//        System.out.print("[");
//        printBinaryTree(mirror);
//        System.out.print("]");
        //二进制中1的个数
//        System.out.println(hammingWeight(000000000000000000000001001011));
        //求1+2+..+n
//        System.out.println(sumNums(9));
        //数组中数字出现的次数I
//        System.out.println(Arrays.toString(singleNumbers(new int[]{4, 1, 4, 6})));
        //数组中数字出现的次数II
        System.out.println(singleNumber(new int[]{9,1,7,9,7,9,7}));
    }


    public static void printBinaryTree(TreeNode root) {
        if (root != null){
            printBinaryTree(root.left);
            System.out.print(root.val +" ");
            printBinaryTree(root.right);
        }
    }
}
