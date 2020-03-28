package com.wx.java_code;


import com.wx.java_code.resource.UF;

/**
 * @Desc leetcode简单问题记录:Java代码
 * @Author wx
 * @Date 20-3-28
 **/
public class MidProblemSet1 {

    public static void main(String[] args) {

        System.out.println();
    }


    /**
     * 面试题 547
     * 朋友圈
     * @param M 关系矩阵
     * @return 朋友圈数
     */
    public int findCircleNum(int[][] M) {
        int length = M[0].length;
        UF uf = new UF(length);
        for(int i = 0 ; i < length ; i ++){
            for(int j = i+1 ; j < length ; j ++){
                if(M[i][j] == 1){
                    uf.union(i, j);
                }
            }
        }
        return uf.getCount();
    }

    /**
     * 面试题 200
     * 岛屿数量
     * @param grid 岛屿分布矩阵
     * @return 岛屿数量
     */
    public static int numIslands(char[][] grid) {
        int m = grid.length;
        UF uf = new UF(m);
        for (int i = 0 ; i < m ; i++) {
            for (int j = i + 1 ; j < m ; j ++){
                if (grid[i][j] == '1') {
                    uf.union(i, j);
                }
            }
        }
        return uf.getCount();
    }
}
