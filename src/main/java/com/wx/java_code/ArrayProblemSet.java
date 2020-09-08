package com.wx.java_code;


import com.wx.java_code.resource.Difference;

/**
 * Created by wx on 20-6-7
 * Description: LeetCode 数组相关问题集合
 * 189. 旋转数组 ${@link #rotate}
 * 1109.航班预订统计${@link #corpFlightBookings}
 */
public class ArrayProblemSet {


    /**
     * 旋转数组
     * @param nums 原数组
     * @param k 旋转点
     */
    public void rotate(int[] nums, int k) {
        rotateHelper(nums, 0, nums.length);
        rotateHelper(nums, 0, k% nums.length - 1);
        rotateHelper(nums, k % nums.length, nums.length - 1);
    }

    /**
     * 对称交换数组元素
     * @param array 原数组
     * @param start 起始位置
     * @param end 结束位置
     */
    private void rotateHelper(int[] array, int start, int end) {
        while (start < end) {
            array[start] = array[start] ^ array[end];
            array[end] = array[start] ^ array[end];
            array[start] = array[start] ^ array[end];
            start++;
            end --;
        }
    }

    /**
     * 航班预订统计
     * @param bookings 航班信息
     * @param n 航班数
     * @return 航班座位数组
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] result = new int[n];
        Difference diff = new Difference(result);
        for (int i = 0; i < bookings.length; i ++) {
            int[] nums = bookings[i];
            diff.increment(nums[0] - 1, nums[1] - 1, nums[2]);
        }
        return diff.result();
    }

}
