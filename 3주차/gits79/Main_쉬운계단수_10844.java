package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_쉬운계단수_10844 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int N = Integer.parseInt(st.nextToken());

        final int mod = 1_000_000_000;

        long[][] dp = new long[N + 1][10];
        // 자릿수가 N + 1 이고 ,시작하는 숫자가 0 ~ 9 10개

        //N=1 일 경우 모든 숫자는 한번씩 등장
        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        //N = 2 부터 N 까지 일때
        for (int i = 2; i <= N; i++) {
            //0~9까지 탐색
            for (int j = 0; j < 10; j++) {
                if (j == 0) { // 끝자리가 0인 경우 앞에 1만 올 수 있음
                    dp[i][j] = dp[i - 1][1] % mod;
                } else if (j == 9) { // 끝자리가 9인 경우 앞에 8만 올 수 있음
                    dp[i][j] = dp[i - 1][8] % mod;
                } else { // 끝자리가 1 ~ 8인 경우 이전 자리수에서 j - 1 또는 j + 1 앞에 올 수 있음
                    // 따라서 두 개를 더한 값이 dp[i][j]가 됨
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
                }
            }
        }
        long sum = 0;
        for (int i = 1; i <= 9; i++) {
            sum += dp[N][i]; // N자리 숫자 중에서 1 ~ 9 로 시작하는 경우의 수를 더함
            sum %= mod;
        }
        System.out.println(sum % mod);

    }


}
