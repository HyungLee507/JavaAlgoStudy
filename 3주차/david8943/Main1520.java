import java.io.*;
import java.util.*;

public class Main1520 {
    static int[][] arr;
    static int[][] dp;
    static int n, m;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};

    static int dfs(int x, int y) {
        // 이미 방문했던 곳
        if (dp[x][y] != -1) return dp[x][y];

        // 목적지 도착
        if (x == n - 1 && y == m - 1) return 1;

        // 처음 방문이면 탐색
        dp[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny] < arr[x][y]) {
                dp[x][y]+=dfs(nx, ny);
            }
        }
        return dp[x][y];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        dp = new int[n][m];

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(0, 0));
    }
}
