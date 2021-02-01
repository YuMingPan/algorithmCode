package com.pym;

import org.junit.Test;

public class Solution {

    @Test
    public void a(){
        System.out.println(new Solution().coinChange(new int[]{2},3));
    }
    // dp(i)凑成总金额所需的最少的硬币个数
    public int coinChange(int[] coins, int amount) {
        // 校验输入
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        // 设置初始状态
        int[] dp = new int[amount + 1];
        // 状态转移方程
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int face : coins) {
                if (i - face < 0 || dp[i - face] < 0) {
                    continue;
                }
                if (dp[i - face] >= min) {
                    continue;
                }
                min = dp[i - face];
            }
            if (min == Integer.MIN_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = min + 1;
            }
        }
        return dp[amount];
    }
}