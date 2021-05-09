package com.wx.java_code.math;

/**
 * 数学问题集合
 *  面试题16.01 交换数字 {@link #swapNumbers}
 */
public class MathProblems {

    /**
     * 面试题16.01 交换数字
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
}
