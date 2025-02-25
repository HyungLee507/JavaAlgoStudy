import java.io.*;
import java.util.*;

public class BOJ_11066 {
	static int N;
	static int[] arr;
	static int[] sum;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N + 1];
			sum = new int[N + 1];
			dp = new int[N + 1][N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i - 1] + arr[i];
			}

			for (int length = 1; length < N; length++) {
				for (int start = 1; start + length <= N; start++) {
					int end = start + length;
					dp[start][end] = Integer.MAX_VALUE;

					for (int mid = start; mid < end; mid++) {
						dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid + 1][end] + sum[end] - sum[start - 1]);
					}
				}
			}
			sb.append(dp[1][N]).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
