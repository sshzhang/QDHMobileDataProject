package com.lifeStudy.algorith;

import java.util.*;

//动态规划 解决袋鼠过河问题
public class niukewang_daishuguohe {

  /*  public static void main(String... args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        //都从1开始
        int[] a = new int[N];//N个数据
        int[] dp = new int[N + 1];//dp[x]表示到x米所需要的最少跳转次数
        int[] V = new int[N + 1];//V[i]表示i是否可以到达.
        for (int i = 0; i < N; i++) {
            a[i] = sc.nextInt();
            dp[i+1] = 100002;
            V[i+1] = 0;//表示不通
        }
        dp[0] = 0;
        V[0] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] == 0) continue;
                if (j + a[j] >= i) dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }

        if (dp[N] == 100002) System.out.println(-1);
        else
            System.out.println(dp[N]);
        sc.close();
    }*/

    public static void main(String... args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        //都从1开始
        int[] a = new int[N + 1];//N个数据
        int[] dp = new int[N + 2];//dp[x]表示到x米所需要的最少跳转次数
        int[] V = new int[N + 2];//V[i]表示i是否可以到达.
        for (int i = 1; i <= N; i++) {
            a[i] = sc.nextInt();
            V[i + 1] = 0;
        }

        dp[1] = 0;//到位置1 只需要0步
        V[1] = 1; //1位置 能到达
        for (int i = 2; i <= 1 + a[1]; i++) {
            dp[i] = 1;
            V[i] = 1;
        }
        for (int i = 2; i <= N; i++) {
            if (a[i] == 0) continue;
            for (int j = i + 1; j <= a[i] + i&&j<=N+1; j++) {
                if ((V[i] == 1) && (V[j] == 0)) {
                    dp[j] = dp[i] + 1;
                    V[j] = 1;
                }
            }
        }
        if (V[N + 1] == 1) System.out.println(dp[N + 1]);
        else System.out.println(-1);

        sc.close();

    }

}
