package com.wx.java_code;

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

    public static void main(String[] args) {
        //判定字符是否唯一
        System.out.println(isUnique("leetcode"));
    }
}
