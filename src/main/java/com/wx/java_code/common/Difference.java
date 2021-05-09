package com.wx.java_code.common;

/**
 * 差分数组
 */
public class Difference {
    private int[] diff;
    //根据数组构造差分数组
    public Difference(int[] nums) {
        diff = new int[nums.length];
        diff[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }
    //给闭区间[i, j]增加或减少val
    public void increment(int i, int j, int val) {
        diff[i] += val;
        if(j + 1 < diff.length) {
            diff[j+1] -= val;
        }
    }
    //根据差分数组反推出数组
    public int[] result() {
        int[] res = new int[diff.length];
        res[0] = diff[0];
        for(int i = 1; i < diff.length; i++) {
            res[i] = res[i-1] + diff[i];
        }
        return res;
    }

}
