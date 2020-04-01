package com.wx.java_code;


import com.wx.java_code.resource.ListNode;

import java.util.*;

/**
 * Created by wx on 19-3-11
 * Description:leetcode简单问题记录:Java代码
 * 问题编号:1,7
 **/

public class EasyProblemSet1 {

    /**
     * 1.两数之和
     */
    public static int[] twoSum(int[] nums, int target) {

        HashMap<Integer,Integer> result = new HashMap<>();

        for(int i = 0 ;i < nums.length ; i++){
            if(result.containsKey(target - nums[i])){
                return new int[]{result.get(target - nums[i]),i};
            }else{
                result.put(nums[i],i);
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * 整数反转
     */
    public static int reverse(int x) {

        long result = 0;

        long xAbs = Math.abs(Long.valueOf(x));

        if(xAbs > Integer.MAX_VALUE){
            return 0;
        }

        while (xAbs != 0){
            long temp = xAbs % 10;
            result = result * 10 + temp;
            xAbs = xAbs / 10;
        }

        if(x > 0 && result < Integer.MAX_VALUE){
            return (int) result;
        }else if(x < 0 && result <= Integer.MAX_VALUE){
            return (int) -result;
        }
        return 0;
    }

    /**
     * 回文数
     */
    public static boolean isPalindrome(int x) {
        if(x < 0 ){
            return false;
        }
        return x == reverse(x);
    }

    /**
     * 最长公共前缀
     */
    public static String longestCommonPrefix(String[] strs) {

        if(strs.length == 0){
            return "";
        }else if(strs.length == 1){
            return strs[0];
        }

        int i = 0;
        for(;i < strs[0].length() ; i++){
            for(int j = 1 ; j < strs.length ; j++){
                if(strs[0].charAt(i) != strs[j].charAt(i)){
                    if(i == 0){
                        return "";
                    }else{
                        return strs[0].substring(0,i-1);
                    }
                }
            }
        }
        return strs[0];
    }

    /**
     * 罗马数字转整数{"I": 1, "V": 5, "X": 10, "L": 50, "C": 100, "D": 500, "M": 1000}
     */
    public static int romanToInt(String s) {
        int result = 0;
        char[] chars = s.toCharArray();
        Map<Character,Integer> dicts = new HashMap<>(7);
        char[] keys = new char[]{'I','V','X','L','C','D','M'};
        int[] values = new int[]{1,5,10,50,100,500,1000};
        int i = 0;
        for(; i < keys.length;i++){
            dicts.put(keys[i],values[i]);
        }
        i = 0;
        for(;i < chars.length;i++){
            if(i > 0 && dicts.get(chars[i]) > dicts.get(chars[i - 1])){
                result += dicts.get(chars[i]) - 2 * dicts.get(chars[i - 1]);
            }else{
                result += dicts.get(chars[i]);
            }
        }
        return result;
    }

    /**
     * 有效的括号
     */
    public static boolean isValid(String s) {
        if(s == null || s.length() == 1){
            return false;
        }else if(s.length() == 0){
            return true;
        }

        Stack<Character> signs = new Stack<>();
        char[] chars = s.toCharArray();
        for(int i = 0;i<chars.length;i++){
            if(chars[i] == '(' || chars[i] == '[' || chars[i] == '{'){
                signs.push(chars[i]);
            }else if(!signs.isEmpty()){
                char peek = signs.peek();
                switch (chars[i]){
                    case ')':
                        if (peek == '(') {
                            signs.pop();
                        }else{
                            return false;
                        }
                        break;
                    case ']':
                        if(peek == '['){
                            signs.pop();
                        }else{
                            return false;
                        }
                        break;
                    case '}':
                        if(peek == '{'){
                            signs.pop();
                        }else{
                            return false;
                        }
                        break;
                }
            }else{
                return false;
            }
        }
        return signs.isEmpty();
    }


    //合并两个有序链表
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode result = dummy;
        while (l1 != null  && l2 != null){
            if(l1.val < l2.val ){
                dummy.next = l1;
                dummy = dummy.next;
                l1 = l1.next;
            }else if(l1.val == l2.val){
                dummy.next = l1;
                dummy = dummy.next;
                l1 = l1.next;
                dummy.next = l2;
                dummy = dummy.next;
                l2 = l2.next;
            }else {
                dummy.next = l2;
                dummy = dummy.next;
                l2 = l2.next;
            }

        }
       if(l1 != null){
           dummy.next = l1;
       }else if(l2 != null){
           dummy.next = l2;
       }

       return result.next;

    }

    /**
     * 统计位数为偶数的数字
     * @param nums 所给数据数组
     * @return 位数为偶数的数字个数
     */
    public static int findNumbers(int[] nums) {
        if (nums == null || nums.length <= 0){
            return 0;
        }
        int count = 0;
        int j = 0;
        for (int i : nums) {
            if (i < 0 || i > 100000) {
                continue;
            }
            if (((int) Math.log10(i) + 1) % 2 == 0) {
                count += 1;
            }
            j = 0;
        }
        return count;
    }

    /**
     * 猜数字
     * @param guess 题目数组
     * @param answer 回答数组
     * @return 猜对个数
     */
    public static int guessNumber(int[] guess, int[] answer) {
        if (guess == null || guess.length != 3
                || answer == null || answer.length != 3) {
            return 0;
        }
        int count = 0, i = 0;
        while (i < 3) {
            if (guess[i] == answer[i]) {
                count += 1;
            }
            i += 1;
        }
        return count;
    }

    /**
     * 整数的各位积和之差
     * @param n 所给数字
     * @return 积和之差
     */
    public static int subtractProductAndSum(int n) {
        if (n < 1 || n > 100000) {
            return 0;
        }
        int sum = 0, product = 1, i = 0;
        while (n != 0) {
            i = n % 10;
            sum += i;
            product *= i;
            n /= 10;
        }
        return product - sum;
    }

    /**
     * 解压缩编码列表
     * @param nums 所给压缩数据
     * @return 解压数据
     */
    public static int[] decompressRLElist(int[] nums) {
        int length = 0, i = 0, j = 0, index = 0;
        for (; i < nums.length ; i+=2) {
            length += nums[i];
        }
        int[] result = new int[length];
        for (i = 0 ; i < nums.length ; i+=2){
            for (j = 0; j < nums[i] ; j++){
                result[index] = nums[i + 1];
                index++;
            }
        }
        return result;
    }

    /**
     * 将数字变成 0 的操作次数
     * @param num
     * @return 操作步数
     */
    public static int numberOfSteps (int num) {
        if (num < 0 || num > 1000000) {
            return 0;
        }
        int count = 0;
        while (num != 0) {
            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num = num - 1;
            }
            count += 1;
        }
        System.out.println("count = "+count);
        return count;
    }


    /**
     * 1108
     * IP地址无效化
     * @param address IP地址
     * @return 无效化地址
     */
    public static String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder(address.length() * 3);
        for (char c : address.toCharArray()) {
            if (c == '.'){
                sb.append("[.]");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 面试题1290
     * 二进制链表转整数
     * @param head 头结点
     * @return 整数
     */
    public int getDecimalValue(ListNode head) {
        StringBuilder sb = new StringBuilder(32);
        while (head != null) {
            sb.append(head.val);
            head = head.next;
        }
        return Integer.parseInt(sb.toString(),2);
    }


    public static void main(String[] args){

        //两数之和
//        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15},9)));
        //整数反转
//        System.out.println(longestCommonPrefix(new String[]{"c","c"}));
        //有效的括号
//        System.out.println(isValid("(]}"));
        //统计位数为偶数的数字
//        System.out.println(findNumbers(new int[]{12,345,2,6,7896}));
        //猜数字
//        System.out.println(guessNumber(new int[]{2,2,3}, new int[]{3,2,1}));
        //整数的各位积和之差
//        System.out.println(subtractProductAndSum(4421));
        //解压缩编码列表
//        System.out.println(decompressRLElist(new int[]{1,2,3,4}));
        //将数字变成 0 的操作次数
//        System.out.println(numberOfSteps(0));
        //IP地址无效化
        System.out.println(defangIPaddr("1.1.1.1"));
    }
}
