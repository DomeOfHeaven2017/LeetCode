package com.wx.java_code.bt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @desc  回溯问题集合
 *  面试题 08.04. 幂集 {@link #subsets}
 *
 * 46. 全排列 {@link #permute}
 * 51. N 皇后 & 面试题 08.12. 八皇后 {@link #solveNQueens}
 * 1863. 找出所有子集的异或总和再求和 {@link #subsetXORSum}
 */
public class BackTrackProblems {

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
    /**
     * 迭代解决方式
     */
    private List<List<Integer>> subsetsIteration(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> temp = new ArrayList<>(result.get(i));
                temp.add(num);
                result.add(temp);
            }
        }
        return result;
    }

    /**
     * 回溯算法解决方式
     */
    private void subsetsBackTrack(List<List<Integer>> result, List<Integer> track, int[] nums, int index) {
        result.add(new ArrayList<>(track));
        for (int i = index; i < nums.length; i++) {
            track.add(nums[i]);
            subsetsBackTrack(result, track, nums, i + 1);
            track.remove(track.size() - 1);
        }
    }
    //*************************************************************************************************
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
    //****************************************************************************************************
    /**
     * 51. N 皇后
     * 面试题 08.12. 八皇后
     * @param n n个皇后
     * @return 棋盘排列结果
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        //初始化参数
        char[][] path = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                path[i][j] = '.';
            }
        }
        solveNQueensBackTrack(n, 0, result, path);
        return result;
    }

    private void solveNQueensBackTrack(int n, int row, List<List<String>> result, char[][] path) {
        if (n == row) {
            List<String> temp = new ArrayList<>();
            for (char[] chars : path) {
                temp.add(String.valueOf(chars));
            }
            result.add(temp);
            return;
        }
        for (int i = 0; i < n; i++){
            if (!isQueensValid(path, row, i)) {
                continue;
            }
            path[row][i] = 'Q';
            solveNQueensBackTrack(n, row + 1, result, path);
            path[row][i] = '.';
        }
    }

    /**
     * 判断当前皇后位置是否符合要求
     * @param path 当前棋盘
     * @param row 当前行
     * @param col 当前列
     * @return 是否有冲突
     * 因为是自顶向下添加的，且每行添加一个，所以只需要判断上方，左上，右上是否有冲突即可
     */
    private boolean isQueensValid(char[][] path, int row, int col) {
        int i = 0, j = 0;
        //上方同列
        for (i = row - 1; i >= 0; i--) {
            if (path[i][col] == 'Q') {
                return false;
            }
        }
        //左上
        for (i = row - 1,  j = col - 1; i >= 0 && j >= 0;i-- , j--) {
            if (path[i][j] == 'Q') {
                return false;
            }
        }
        //右上
        for (i = row - 1, j = col + 1; i >= 0 && j < path[0].length; i--, j++) {
            if (path[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
//********************************************************************************************************

    /**
     * 1863. 找出所有子集的异或总和再求和
     * @param nums 数据集
     * @return 子集异或总和
     */
    public int subsetXORSum(int[] nums) {
        if (nums.length == 1) return nums[0];
        subsetXorSumDfs(nums, 0, 0);
        return xorSum;
    }
    /**
     * 异或总和，定义为全局变量，方便修改
     */
    int xorSum = 0;
    /**
     * @param nums 源数组
     * @param i 数组索引，标记位置
     * @param subsetXor 子集的异或值
     */
    private void subsetXorSumDfs(int[] nums, int i, int subsetXor) {
        //终止条件，获得一个子集
        if (i == nums.length) {
            xorSum += subsetXor;
            return;
        }
        //选择当前元素到子集中
        subsetXorSumDfs(nums, i + 1, subsetXor ^ nums[i]);
        //不选择当前元素到子集中
        subsetXorSumDfs(nums, i + 1, subsetXor);
    }
}
