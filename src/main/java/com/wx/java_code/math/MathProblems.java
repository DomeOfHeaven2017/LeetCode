package com.wx.java_code.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 数学问题集合
 *  面试题16.01 交换数字 {@link #swapNumbers}
 *  面试题 16.07. 最大数值 {@link #maximum}
 *
 * 13. 罗马数字转整数 {@link #romanToInt}
 * 204. 计数质数 {@link #countPrimes}
 * 231. 2 的幂 {@link #isPowerOfTwo}
 * 342. 4的幂 {@link #isPowerOfFour}
 * 1310. 子数组异或查询 {@link #xorQueries}
 * 1323. 6 和 9 组成的最大数字 {@link #maximum69Number}
 *  1734. 解码异或后的排列 {@link #decode}
 *  1822. 数组元素积的符号 {@link #arraySign}
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
     * 面试题 16.07. 最大数值
     * @param a 参数a
     * @param b 参数b
     * @return 最大值
     */
    public int maximum(int a, int b) {
        //获取差值,避免溢出，转成long类型
        long diff = (long) a - (long) b;
        //由于diff是long类型，所以需要右移63位得到符号位，负数移动后为-1,正数移动后为0
        int k = (int) (1 + (diff  >> 63));
        //如果a > b,则 k = 1, k*a+(!k)*b = a;
        //如果a < b,则 k = 0, k*a+(!k)*b = b;
        return k * a + (k^1) * b;
    }

    /**
     * 13. 罗马数字转整数
     * @param s 罗马数字字符串
     * @return 转换后的整数
     */
    public int romanToInt(String s) {
        int result = 0;
        //将罗马数字字母与整数对应存储到map中，方便获取
        Map<Character, Integer> map = new HashMap<Character, Integer>() {{
            put('I', 1); put('V', 5); put('X', 10); put('L', 50);
            put('C', 100); put('D', 500); put('M', 1000);
        }};
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i ++) {
            //遍历罗马数字字符串，根据规则如果小的数值在大的前面就要减去这个小的值
            //反之需要加上这个小的值
            int value = map.get(chars[i]);
            if (i < chars.length - 1 && value < map.get(chars[i+1])) {
                result -= value;
            } else {
                result += value;
            }
            //另一种解法是先加上最小值，然后遇到小的值在前面时就加上大的值并减去2倍的小的值
            //即 max - min = min + max - 2 * min
            if (i > 0 && value > map.get(chars[i - 1])) {
                result += value - 2 * map.get(chars[i - 1]);
            } else {
                result += value;
            }
        }
        return result;
    }

    /**
     * 204. 计数质数
     * @param n 数字n
     * @return 小于n的素数个数
     */
    public int countPrimes(int n) {
        //初始化n项的数组，表示1~n是否为素数,初始全部设为true
        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);
        //从2开始遍历，由于对称性，只需遍历到i^2
        for (int i = 2; i * i < n; i++) {
            //如果当前的值为素数，则该素数的整数倍都不是素数
            if (primes[i]) {
                for (int j = i * i; j < n; j += i) {
                    primes[j] = false;
                }
            }
        }
        int count = 0;
        //计算素数个数
        for (int i = 2; i < n; i++) {
            if (primes[i]) count++;
        }
        return count;
    }

    /**
     * 231. 2 的幂
     * @param n 整数N
     * @return 是否为2的幂
     */
    public boolean isPowerOfTwo(int n) {
        //一个数为2的幂，则其二进制只有最高位一个1
        //若n为2的幂，则n的二进制除最高位为1之外其余为0；则n-1的二进制除最高位为0之外，其余为1
        //则n & n -1可以把n的最低位的1变为0，(n & n-1 == 0)可检测n是否只有最高位一个1;
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * 342. 4的幂
     * @param n 整数n
     * @return 是否为4的幂
     */
    public boolean isPowerOfFour(int n) {
        //一个数为4的幂，则必为2的幂
        //若n为2的幂，则n的二进制有且仅有一个1，且除这个1之外有偶数个0
        //所以构建一个偶数位为1，奇数位为0的数，按位与n，若n为4的幂，则结果为0
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }

    /**
     * 1310. 子数组异或查询
     * @param arr 源数组
     * @param queries 查询数组
     * @return 异或结果数组
     */
    public int[] xorQueries(int[] arr, int[][] queries) {
        //构建前缀异或数组，即xors[i] = arr[0]^....arr[i]
        //xors[left - 1]^xors[right]=异或[0...left-1]^异或[0...left-1]^异或[left...right]=异或[left...right]
        int m = arr.length, n = queries.length;
        int[] result = new int[n];
        //前缀异或数组，xor[0] = 0;
        int[] xors = new int[m + 1];
        for (int i = 1; i < m + 1; i ++) {
            xors[i] = xors[i - 1] ^ arr[i - 1];
        }
        for (int i = 0 ; i < n ; i ++) {
            int left = queries[i][0] + 1;
            int right = queries[i][1] + 1;
            result[i] = xors[left - 1] ^ xors[right];
        }
        return result;
    }

    /**
     * 1323. 6 和 9 组成的最大数字
     * @param num 源数据
     * @return 翻转一次组成的最大数据
     */
    public int maximum69Number (int num) {
        //转换为字符数组方便操作
        char[] chars = String.valueOf(num).toCharArray();
        //从高位搜索转换第一个6为9
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '6') {
                chars[i] = '9';
                break;
            }
        }
        return Integer.parseInt(String.copyValueOf(chars));
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

    /**
     * 1822. 数组元素积的符号
     * @param nums 数组数据
     * @return 元素积符号
     */
    public int arraySign(int[] nums) {
        //计算负数的个数
        int negative = 0;
        for (int num : nums) {
            if (num == 0) return 0;
            if (num < 0) negative ++;
        }
        return negative % 2 == 0 ? 1 : -1;
    }
}
