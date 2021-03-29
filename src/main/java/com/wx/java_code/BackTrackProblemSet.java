package com.wx.java_code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wx on 21-3-28
 * @desc 回溯算法问题集合
 *  面试题 08.04. 幂集 {@link #subsets}
 *  46. 全排列 {@link #permute}
 */
public class BackTrackProblemSet {

    public static void main(String[] args) {

    }

    /**
     * 面试题 08.04. 幂集
     * @param nums 集合元素
     * @return 集合幂集
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsetsBackTrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void subsetsBackTrack(List<List<Integer>> result, List<Integer> track, int[] nums, int index) {
        result.add(new ArrayList<>(track));
        for (int i = index; i < nums.length; i++) {
            track.add(nums[i]);
            subsetsBackTrack(result, track, nums, i + 1);
            track.remove(track.size() - 1);
        }
    }


    /**
     * 46. 全排列
     * @param nums 排列元素数组
     * @return 全排列结果
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> track = new LinkedList<>();
        permuteBackTrack(track, result, nums);
        return result;
    }

    private static void  permuteBackTrack(List<Integer> track, List<List<Integer>> res, int[] nums) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int num : nums) {
            if (track.contains(num)) continue;
            track.add(num);
            permuteBackTrack(track, res, nums);
            track.remove(track.size() - 1);
        }
    }




}
