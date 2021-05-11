package com.wx.java_code.math;

/**
 * 数学问题集合
 *  面试题16.01 交换数字 {@link #swapNumbers}
 *
 *  1734. 解码异或后的排列 {@link #decode}
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

    /**
     * 1734. 解码异或后的排列
     * @param encoded 编码异或后的数据
     * @return 解码异或后的数据
     */
    public int[] decode(int[] encoded) {
        int length = encoded.length + 1;
        //题目中该数据为前n个正整数，只是排列不同，则异或后的值相同
        int total = 0, i = 1;
        for (; i <= length ; i++) {
            total ^= i;
        }
        //要得到整个序列，则需要获取到第一个数据，目前已得到所有数据异或后的值total
        //x1 ^ x2 ^ ... xn = total,且n为奇数，则除了第一项剩下的项数为偶数
        //则编码数组 encode[0] = x2 ^ x3,encode[1] = x3 ^ x4, encode[2] = x4^x5
        //如果取encode的奇数项(数组小标从0开始故为奇数项)并将其异或则可得到x2^x3^x4^x5...xn的值evenXor
        //再将evenXor ^ total根据异或性质，相同数据相消，则可得到x1即第一项的值
        int evenXor = 0;
        for (i = 1; i < encoded.length; i+=2) {
            evenXor ^= encoded[i];
        }
        int first= total ^ evenXor;
        int[] result = new int[length];
        result[0] = first;
        for (i = 1 ;i < length ; i++) {
            result[i] = encoded[i - 1] ^ result[i - 1];
        }
        return result;
    }
}
