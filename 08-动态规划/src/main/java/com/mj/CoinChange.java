package com.mj;

public class CoinChange {

    public static void main(String[] args) {
//		System.out.println(coins5(41, new int[] {1, 5, 25, 20}));
        System.out.println(coins2(6));
        System.out.println("coins2 被调用了 " + count + " 次");
        // fib(40)

        // dp(i) 第i项斐波那契数
        // dp(i) = dp(i - 1) + dp(i - 2)

        // dp(41) = 凑够41需要的最少硬币数量 = min { dp(40), dp(36), dp(16), dp(21) } + 1
        // dp(41 - 1) = dp(40) = 凑够40需要的最少硬币数量
        // dp(41 - 5) = dp(36) = 凑够36需要的最少硬币数量
        // dp(41 - 25) = dp(16) = 凑够16需要的最少硬币数量
        // dp(41 - 20) = dp(21) = 凑够21需要的最少硬币数量
        // min { dp(40), dp(36), dp(16), dp(21) } + 1
    }

    /**
     * 凑齐n分钱所需要的最小硬币个数，凑不齐返回-1
     */
    static int coins5(int n, int[] faces) {
        if (n < 1 || faces == null || faces.length == 0) {
            return -1;
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int face : faces) {
                // 当前i小于面值所以凑不齐;之前就凑不齐导致当前也凑不齐
                if (i < face || dp[i - face] < 0) {
                    continue;
                }
                if (dp[i - face] >= min) {
                    continue;
                }
                min = dp[i - face];
            }
            if (min == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = min + 1;
            }
        }
        return dp[n];
    }

    static int coins4(int n) {
        if (n < 1) {
            return -1;
        }
        int[] dp = new int[n + 1];
        // faces[i]是凑够i分时最后那枚硬币的面值
        int[] faces = new int[dp.length];
        for (int i = 1; i <= n; i++) {
            int min = dp[i - 1];
            faces[i] = 1;

            if (i >= 5 && dp[i - 5] < min) {
                min = dp[i - 5];
                faces[i] = 5;
            }
            if (i >= 20 && dp[i - 20] < min) {
                min = dp[i - 20];
                faces[i] = 20;
            }
            if (i >= 25 && dp[i - 25] < min) {
                min = dp[i - 25];
                faces[i] = 25;
            }
            dp[i] = min + 1;
            print(faces, i);
        }
//		print(faces, n);
        return dp[n];
    }

    static void print(int[] faces, int n) {
        System.out.print("[" + n + "] = ");
        while (n > 0) {
            System.out.print(faces[n] + " ");
            n -= faces[n];
        }
        System.out.println();
    }

    /**
     * 递推（自底向上），即迭代，在这里叫递推比较合适
     */
    static int coins3(int n) {
        if (n < 1) {
            return -1;
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            if (i >= 1) {
                min = Math.min(dp[i - 1], min);
            }
            // 上面两句可以合并为 int min = dp[i - 1]; 即每次循环的最小值为前一次的结果
            if (i >= 5) {
                min = Math.min(dp[i - 5], min);
            }
            if (i >= 20) {
                min = Math.min(dp[i - 20], min);
            }
            if (i >= 25) {
                min = Math.min(dp[i - 25], min);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    /**
     * 记忆化搜索（自顶向下的调用）已经计算过的值进行保存
     */
    static int coins2(int n) {
        // 不合理的参数处理
        if (n < 1) {
            return -1;
        }
        // 动态规划时，数组名称约定为dp；长度为n + 1，索引按n去查，数组长度取决于n而不是面值
        int[] dp = new int[n + 1];

        // 已确定的结果预先存储，否则当n为1、5、20、25的时候，我该返回什么值，数组需要有序，否则break改为continue
        int[] faces = {1, 5, 20, 25};
        for (int face : faces) {
            // 比如初始传入19的话
            if (n < face) {
                break;
            }
            // dp[20]将会越界
            dp[face] = 1;
        }

        return coins2(n, dp);
    }

    // dp数组随参数传入
    static int coins2(int n, int[] dp) {
        count++;
        if (n < 1) {
            return Integer.MAX_VALUE;
        }
        // 如果没有存过该值，则计算该值，并存储
        if (dp[n] == 0) {
            int min1 = Math.min(coins2(n - 25, dp), coins2(n - 20, dp));
            int min2 = Math.min(coins2(n - 5, dp), coins2(n - 1, dp));
            // 之前直接return这个值，现在先存储再将存储的值返回
            dp[n] = Math.min(min1, min2) + 1;
        }
        return dp[n];
    }

    /**
     * 暴力递归（自顶向下的调用，出现了重叠子问题）
     * 凑够n分钱所需要的最少硬币枚数
     */
    static long count = 0;

    static int coins1(int n) {
        count++;
        if (n < 1) {
            // 不仅是对输入的校验，后续取min值的时候，不合理的结果应该被抛弃
            return Integer.MAX_VALUE;
        }
        // 递归基 想凑25、20、5、1分钱的话只需要1枚硬币
        if (n == 25 || n == 20 || n == 5 || n == 1) {
            return 1;
        }
        int c1 = coins1(n - 25);
        int c2 = coins1(n - 20);
        int c3 = coins1(n - 5);
        int c4 = coins1(n - 1);

        int min1 = Math.min(c1, c2);
        int min2 = Math.min(c3, c4);
        return Math.min(min1, min2) + 1;
    }
}
