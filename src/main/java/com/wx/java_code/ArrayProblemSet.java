package com.wx.java_code;


import com.wx.java_code.resource.Difference;

import java.util.Arrays;

/**
 * Created by wx on 20-6-7
 * Description: LeetCode 数组相关问题集合
 * 14. 最长公共前缀 ${@link #longestCommonPrefix}
 * 26.删除排序数组中的重复项 ${@link #removeDuplicates}
 * 27. 移除元素 ${@link #removeElement}
 * 189. 旋转数组 ${@link #rotate}
 * 283. 移动零  ${@link #moveZeroes}
 *  350. 两个数组的交集II ${@link #intersect}
 * 461. 汉明距离 ${@link #hammingDistance}
 * 867. 转置矩阵${@link #transpose}
 * 1109.航班预订统计${@link #corpFlightBookings}
 * 1470.重新排列数组 ${@link #shuffle}
 */
public class ArrayProblemSet {

    public static void main(String[] args) {
        System.out.println(hammingDistance(1,4));;
    }

    /**
     * 14. 最长公共前缀
     * @param strs 字符串数组
     * @return 公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for
    }

    /**
     * 26.删除排序数组中的重复项 
     * @param nums 原始排序数组
     * @return 去重后的数组末尾序号
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0, fast = 0;
        while(fast < nums.length) {
            if(nums[fast] != nums[slow]) {
                slow ++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow+1;
    }

    /**
     * 27. 移除元素
     * @param nums 原数组
     * @param val 待移除元素
     * @return 移除后的数组末尾序号
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow ++;
            }
            fast ++;
        }
        return slow + 1;
    }
    
    /**
     * 189.旋转数组
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
     * 283. 移动零
     * @param nums 原数组
     */
    public void moveZeroes(int[] nums) {
        int index = removeElement(nums, 0);
        for (int i = index; i< nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 350. 两个数组的交集II
     * @param nums1 集合1
     * @param nums2 集合2
     * @return 两个集合的交集
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        int i = 0, j = 0, k = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i ++;
            } else if (nums1[i] > nums2[j]) {
                j ++;
            } else {
                result[k ++] = nums1[i];
                i ++;
                j ++;
            }
        }
        return Arrays.copyOf(result, k);
    }

    /**
     * 汉明距离
     * @param x 参数x
     * @param y 参数y
     * @return x与y之间的汉明距离
     */
    public static int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0 ){
            if ((xor & 1) == 1) {
                distance++;
            }
            xor = xor >> 1;
        }
        return distance;
    }

    /**
     * 转置矩阵
     * @param A 原矩阵
     * @return 转置矩阵
     */
    public int[][] transpose(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int[][] result = new int[n][m];
        for (int i = 0;i < m; i ++) {
            for (int j = 0; j < n; j++){
                result[j][i] = A[i][j];
            }
        }
        return result;
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

    /**
     * 重新排列数组
     * @param nums 原数组
     * @param n 中间索引
     * @return 重排数组
     */
    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[2*n];
        for (int i = 0; i <  n; i ++) {
            result[i*2] = nums[i];
            result[i*2 + 1] = nums[n+i];
        }
        return result;
    }

}
