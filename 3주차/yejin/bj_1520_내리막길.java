import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static int[][] height, dp;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        height = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                height[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; // 아직 방문하지 않은 상태
            }
        }

        System.out.println(dfs(0, 0));
    }

    public static int dfs(int x, int y) {
        if (x == M - 1 && y == N - 1) return 1; // 도착점 도달 시 경로 1개
        if (dp[x][y] != -1) return dp[x][y]; // 이미 계산된 값이면 재사용

        dp[x][y] = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && nx < M && ny >= 0 && ny < N && height[nx][ny] < height[x][y]) {
                dp[x][y] += dfs(nx, ny); // 가능한 경로 수 더하기
            }
        }
        return dp[x][y];
    }
}
