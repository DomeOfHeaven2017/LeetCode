package com.wx.java_code.resource;

public class PrefixSum {
    private int[] prefix;
    //根据所给数组构造前缀和数组
    public PrefixSum(int[] nums) {
        prefix = new int[nums.length + 1];
        for(int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i-1] + nums[i-1];
        }
    }
    //计算闭区间[i, j]的累加和
    public int query(int i, int j) {
        return prefix[j+1] - prefix[i];
    }
}
