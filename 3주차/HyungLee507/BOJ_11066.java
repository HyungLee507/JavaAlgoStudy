package backjoon.study.week3;

import java.util.Scanner;

public class BOJ_11066 {
    static int T;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            int files[];
            int K = sc.nextInt();
            files = new int[K + 1];
            int dp[][];
            dp = new int[K + 1][K + 1];

            files[1] = sc.nextInt();
            for (int i = 2; i <= K; i++) {
                int tmp = sc.nextInt();
                files[i] = tmp + files[i - 1];
            }

            for (int gap = 1; gap < K; gap++) {
                for (int start = 1; start + gap <= K; start++) {
                    int end = start + gap;
                    dp[start][end] = Integer.MAX_VALUE;

                    for (int mid = start; mid < end; mid++) {
                        dp[start][end] = Math.min(dp[start][end],
                                dp[start][mid] + dp[mid + 1][end] + files[end] - files[start - 1]);
                    }
                }
            }
            System.out.println(dp[1][K]);
        }
    }
}