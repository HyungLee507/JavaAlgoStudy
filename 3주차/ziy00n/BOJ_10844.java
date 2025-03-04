import java.io.*;
import java.util.*;

public class BOJ_10844 {
	static final int mod = 1000000000;
	static long[][] dp;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		dp = new long[N + 1][10];

		for (int i = 1; i < 10; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0) {
					dp[i][0] = dp[i - 1][1] % mod;
				} else if (j == 9) {
					dp[i][9] = dp[i - 1][8] % mod;
				} else {
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
				}
			}
		}

		long result = 0;
		for (int i = 0; i < 10; i++) {
			result += dp[N][i];
		}

		System.out.println(result % mod);
	}
}