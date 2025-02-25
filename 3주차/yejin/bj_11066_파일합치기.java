import java.io.*;
import java.util.*;

public class Main {
    static int T;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());
            int[] files = new int[K + 1];
            int[][] dp = new int[K + 1][K + 1];
            int[] sum = new int[K + 1];  // 누적합

            // 파일 크기 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + files[i];
            }

            // dp[1][2] dp[2][3] dp[3][4]
            // dp[1][3] dp[2][4]
            // dp[1][4]
            // 우리가 사용할 값들은 연속 두 개를 합친 비용, 세 개를 합친 비용, 네 개를 합친 비용...
            // 요런 식으로 가기 때문에 해당하는 값만 DP 테이블 초기화
            for (int gap = 1; gap < K; gap++) { // 연속 n개를 합칠 때 1번째와 n번째의 사이에 몇 개가 있는지
                for (int start = 1; start + gap <= K; start++) { // 연속되는 개수가 K보다 같거나 작아야 하고
                    int end = start + gap; // 연속되는 마지막 파일
                    dp[start][end] = Integer.MAX_VALUE;

                    for (int mid = start; mid < end; mid++) { // 어디서 뽀개게 되는가
                        dp[start][end] = Math.min(dp[start][end], // 특정 start와 end에 대해서 최소의 dp[start][end]를 만들어주는 mid를 찾음
                                dp[start][mid] + dp[mid + 1][end] + (sum[end] - sum[start - 1]));
                    }
                }
            }

            System.out.println(dp[1][K]);
        }
    }
}
