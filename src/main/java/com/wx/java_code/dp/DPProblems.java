package com.wx.java_code.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wx on 21-3-28
 * @desc 动态规划问题集合
 *  322. 零钱兑换{@link #coinChange}
 */
public class DPProblems {

    /**
     * 322. 零钱兑换
     * @param coins 不同面额硬币集合
     * @param amount 总面额
     * @return 凑成总面额所需最少硬币数
     */
    public static int coinChange(int[] coins, int amount) {
        //1.
        Map<Integer, Integer> map = new HashMap<>(coins.length);
        return coinChangeDp(coins, amount, map);
    }
    private static int coinChangeDp(int[] coins,int amount, Map<Integer, Integer> map) {
        if (map.containsKey(amount)) return map.get(amount);
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = coinChangeDp(coins, amount - coin, map);
            if (sub == -1) continue;;
            res = Math.min(res, 1 + sub);
        }
        map.put(amount, res == Integer.MAX_VALUE ? -1 : res);
        return map.get(amount);
    }


}
