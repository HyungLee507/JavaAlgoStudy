import java.io.*;
import java.util.*;

public class BOJ_1520 {
	static int N, M;
	static int[][] board;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); // y축
		N = Integer.parseInt(st.nextToken()); // x축

		board = new int[M][N];
		dp = new int[M][N];

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		System.out.println(dfs(0,0));
		br.close();
	}

	static int dfs(int r, int c) {
		if(r == M-1 && c == N-1) { // 도착지점 도달
			return 1;
		}

		if(dp[r][c] != -1) { // 방문했으면 저장된 값 리턴
			return dp[r][c];
		}

		// 처음 방문하면 0으로 초기화 후 탐색
		dp[r][c] = 0;

		for(int d = 0; d < 4; d++) {
			int rr = r + dy[d];
			int cc = c + dx[d];

			if(0 > rr || rr >= M || 0 > cc || cc >= N) continue;
			if(board[r][c] <= board[rr][cc]) continue;

			dp[r][c] += dfs(rr, cc);
		}

		return dp[r][c];
	}
}