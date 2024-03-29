package com.wx.java_code.aimoffer;

import com.wx.java_code.common.ListNode;
import com.wx.java_code.common.TreeNode;

import java.util.*;

/**
 * @Desc 剑指Offer习题解答
 * @Author wx
 * 剑指 Offer 03 数组中重复的数字 {@link #findRepeatNumber}
 * 剑指 Offer 04 二维数组中的查找 {@link #findNumberIn2DArray}
 * 剑指 Offer 05 替换空格 {@link #replaceSpace}
 * 剑指 Offer 06 从尾到头打印链表 {@link #reversePrint}
 * 剑指 Offer 10-I 斐波那契数列 {@link #fib}
 * 剑指 Offer 10-II 青蛙跳台阶问题 {@link #numWays}
 * 剑指 Offer 11 旋转数组的最小数字 {@link #minArray}
 * 剑指 Offer 14-I 剪绳子 {@link #cuttingRope}
 * 剑指 Offer 15 二进制中1的个数 {@link #hammingWeight}
 * 剑指 Offer 16 数值的整数次方 {@link #myPow}
 * 剑指 Offer 17 打印从1到最大的n位数 {@link #printNumbers}
 * 剑指 Offer 18 删除链表的节点 {@link #deleteNode}
 * 剑指 Offer 22 链表中倒数第k个节点 {@link #getKthFromEnd}
 * 剑指 Offer 24 反转链表 {@link #reverseList}
 * 剑指 Offer 25 合并两个排序的链表 {@link #mergeTwoLists}
 * 剑指 Offer 27 二叉树的镜像 {@link #mirrorTree}
 * 剑指 Offer 29 顺时针打印矩阵 {@link #spiralOrder}
 * 剑指 Offer 40 最小的k个数 {@link #getLeastNumbers}
 *剑指 Offer 50 第一个只出现一次的字符 {@link #firstUniqChar}
 *剑指 Offer 53-I 在排序数组中查找数字I {@link #search}
 *剑指 Offer 55 - I 二叉树的深度 {@link #maxDepth}
 *剑指 Offer 55-II 平衡二叉树 {@link #isBalanced}
 *剑指 Offer 56-I 数组中数字出现的次数 I {@link #singleNumbers(int[])}
 *剑指 Offer 56-II 数组中数字出现的次数 II {@link #singleNumber(int[])}
 *剑指 Offer 57 和为s的两个数字 {@link #twoSum}
 *剑指 Offer 58 - II 左旋转字符串 {@link #reverseLeftWords}
 *剑指 Offer 64 求1+2+...+n {@link #sumNums}
 */
public class AimOfferProblems {

    /**
     * 剑指 Offer 03 数组中重复的数字
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
     * 剑指 Offer 04 二维数组中的查找
     * @param matrix 二维数组
     * @param target 目标数字
     * @return 目标数字是否存在
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null) return false;
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
//        int i = m - 1, j = 0;
//        while (i >= 0 && j < n) {
//            if (matrix[i][j] > target) {
//                i--;
//            } else if (matrix[i][j] < target) {
//                j++;
//            } else {
//                return true;
//            }
//        }
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] > target) {
                j--;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer 05 替换空格
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
     * 剑指 Offer 06 从尾到头打印链表
     * @param head 链表头结点
     * @return 打印结果数组
     */
    public int[] reversePrint(ListNode<Integer> head) {
        int count = 0;
        ListNode<Integer> temp = head;
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


    public static final long THRES_HOLD = 1000000007;
    /**
     * 剑指 Offer 10-I 斐波那契数列
     * @param n 所求第n项
     * @return 斐波那契数列第n项
     */
    public static int fib(int n) {
        //递归法
//        if (n < 1) {
//            return n;
//        }
//        return fib(n - 1) + fib(n - 2);
        //循环法
        if (n <= 1) {
            return n;
        }
        long num1 = 0, num2 = 1;
        long temp = 0;
        int i = 2;
        while (i <= n) {
            temp = (num1 + num2)%THRES_HOLD;
            num1 = num2;
            num2 = temp;
            i ++;
        }
        return (int) temp;

        //公式法
//        double temp = Math.sqrt(5);
//        return (int) ((Math.pow((1 + temp) / 2, n) - Math.pow((1 - temp) / 2, n)) / temp % THRES_HOLD) ;
    }


    /**
     * 剑指 Offer 10-II 青蛙跳台阶问题
     * @param n n级台阶
     * @return n级台阶的跳法
     */
    public static int numWays(int n) {
        if (n <= 1) {
            return 1;
        }
        long num1 = 0, num2 = 1;
        long temp = 0;
        int i = 0;
        while (i < n) {
            temp = (num1 + num2)%1000000007;
            num1 = num2;
            num2 = temp;
            i ++;
        }
        return (int) temp;
    }

    /**
     * 剑指 Offer 11 旋转数组的最小数字
     * @param numbers 所给数组
     * @return 最小数字
     */
    public int minArray(int[] numbers) {
        int l = 0, h = numbers.length - 1;
        while (l < h) {
            int mid = (( h - l ) >> 2 ) + l;
            if (numbers[h] > numbers[mid]) {
                h = mid;
            } else if (numbers[h] < numbers[mid]) {
                l = mid + 1;
            } else {
                h -- ;
            }
        }
        return numbers[l];
    }

    /**
     * 剑指 Offer 14-I 剪绳子
     * @param n 绳子长度
     * @return 最大乘积
     */
    public int cuttingRope(int n) {
        if (n <= 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int time3 = n / 3;
        if (n - 3 * time3 == 1) {
            time3 -= 1;
        }
        int time = n - 3 * time3;
        if (time == 0) {
            return (int) Math.pow(3, time3);
        } else {
            return (int) (Math.pow(3, time3) * time);
        }

//        int[] rope = new int[n + 1];
//        int max = 0;
//        rope[0] = 0;
//        rope[1] = 1;
//        rope[2] = 2;
//        rope[3] = 3;
//        for (int i = 4 ; i <= n ; i ++) {
//            for (int j = 1 ; j <= i / 2 ; j ++){
//                int temp = rope[j]*rope[i-j];
//                if (max < temp) {
//                    max = temp;
//                }
//            }
//            rope[i] = max;
//        }
//        return rope[n];
    }

    /**
     * 剑指 Offer 15 二进制中1的个数
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
     * 剑指 Offer 16 数值的整数次方
     * @param x 底数
     * @param n 幂数
     * @return 次方结果
     */
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (x == 0) return 0;
        double temp = x;
        long i = n;
        if (i < 0) {
            temp = 1 / x;
            i = -i;
        }
        double multi = 1;
        while (i > 0) {
            if ((i & 1) == 1) {
                multi *= temp;
            }
            temp *= temp;
            i = i >> 1;
        }
        return multi;
    }

    /**
     * 剑指 Offer 17 打印从1到最大的n位数
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
     * 剑指 Offer 18 删除链表的节点
     * @param head 所给链表头结点
     * @param val 要删除的节点值
     * @return 删除后的链表头结点
     */
    public ListNode<Integer> deleteNode(ListNode<Integer> head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val.equals(val)) {
            return head.next;
        }
        ListNode<Integer> node = head, temp = head;
        head = head.next;
        while (head != null) {
            if (head.val.equals(val)) {
                temp.next = head.next;
                head.next = null;
                break;
            }
            temp = head;
            head = head.next;
        }
        return node;
    }

    /**
     * 剑指 Offer 22 链表中倒数第k个节点
     * @param head 链表头节点
     * @param k
     * @return 倒数第k个节点
     */
    public ListNode<Integer> getKthFromEnd(ListNode<Integer> head, int k) {
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
        return p;
    }

    /**
     * 剑指 Offer 24 反转链表
     * @param head 链表头结点
     * @return 反转后的链表头节点
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

    /**
     * 剑指 Offer 25 合并两个排序的链表
     * @param l1 递增链表1头结点
     * @param l2 递增链表2头结点
     * @return 合并后的链表头结点
     */
    public ListNode<Integer> mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2) {
        ListNode<Integer> head,temp;
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
     * 剑指 Offer 27 二叉树的镜像
     * @param root 根节点
     * @return 镜像二叉树根节点
     */
    public TreeNode<Integer> mirrorTree(TreeNode<Integer> root) {
        if (root == null){
            return null;
        }
        TreeNode<Integer> node = root.left;
        root.left = root.right;
        root.right = node;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

    /**
     * 剑指 Offer 29 顺时针打印矩阵
     * @param matrix 二维数组
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[]{};
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] result = new int[m * n];
        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;
        int i, count = 0;
        while (top <= bottom && left <= right) {
            for (i = left ; i <= right ; i ++) {
                result[count++] = matrix[top][i];
            }
            top ++;
            if (top > bottom) break;

            for (i = top ; i <= bottom ; i ++) {
                result[count++] = matrix[i][right];
            }
            right --;
            if (left > right) break;

            for (i = right ; i >= left ; i --) {
                result[count++] = matrix[bottom][i];
            }
            bottom --;
            if (top > bottom) break;

            for (i = bottom ; i >= top ; i --) {
                result[count++] = matrix[i][left];
            }
            left ++;
            if (left > right) break;
        }
        return result;
    }

    /**
     * 剑指 Offer 40 最小的k个数
     * @param arr 原数组
     * @param k
     * @return 最小的k个数组成的集合
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        Arrays.sort(arr);
        return Arrays.copyOf(arr, k);
    }


    /**
     * 剑指 Offer 50 第一个只出现一次的字符
     * @param s 原字符串
     * @return 只出现一次的字符
     */
    public char firstUniqChar(String s) {
//        if (s == null || s.length() == 0) return ' ';
//        char result = ' ';
//        Map<Character, Integer> map = new HashMap<>(s.length());
//        for (Character c : s.toCharArray()) {
//            if (map.containsKey(c)) {
//                map.put(c, 1);
//            } else {
//                map.put(c, 0);
//            }
//        }
//        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
//            if (entry.getValue() == 0) {
//                result = entry.getKey();
//                break;
//            }
//        }
//        return result;
        return ' ';
    }


    /**
     * 剑指 Offer 53-I 在排序数组中查找数字I
     * @param nums 原数组
     * @param target 目标数字
     * @return 目标数字在数组中出现的次数
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length <= 0) return 0;
        int l = 0, h = nums.length - 1;

        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (nums[mid] <= target) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        int right = l;
        l = 0; h = nums.length - 1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (nums[mid] >= target) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return right - h - 1;
    }

    /**
     * 剑指 Offer 55 - I 二叉树的深度
     * @param root 二叉树根节点
     * @return 深度
     */
    public static int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    /**
     * 剑指 Offer 55-II 平衡二叉树
     * @param root 根节点
     * @return 是否为平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
//        if (root == null) {
//            return true;
//        } else {
//            return isBalanced(root.left) && isBalanced(root.right) && (Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1);
//        }
        return (root == null) || (isBalanced(root.left) && isBalanced(root.right) && (Math.abs(
                maxDepth(root.left) - maxDepth(root.right)) <= 1));
    }

    /**
     * 剑指 Offer 56-I 数组中数字出现的次数 I
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
     * 剑指 Offer 56-II 数组中数字出现的次数 II
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
     * 剑指 Offer 57 和为s的两个数字
     * @param nums 原递增数组
     * @param target 和
     * @return 和为target的两个数字
     */
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 0) return nums;
        for (int i = 0 ; i < nums.length ; i ++) {
            int item = target - nums[i];
            if (binarySearch(nums, item)) {
                return new int[]{nums[i], item};
            }
        }
        return new int[]{};
    }

    /**
     * 二分查找
     * @param nums 原数组
     * @param item 目标数字
     * @return 是否在数组中
     */
    private static boolean binarySearch(int[] nums, int item) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int mid = ((h - l) >> 1) + l;
            if (item > nums[mid]) {
                l = mid + 1;
            } else if (item < nums[mid]) {
                h = mid;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer 58 - II 左旋转字符串
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
     * 剑指 Offer 64 求1+2+...+n
     * @param n 最大求值
     * @return n阶和
     */
    public static int sumNums(int n) {
        int sum = n;
        boolean is = (n > 0) && ((sum = sum + sumNums(n - 1)) > 0);
        return sum;
    }
}
