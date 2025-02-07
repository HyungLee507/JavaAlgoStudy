package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_내리막길_1520 {

    static int[][] map, dp;
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];
        for(int i = 0; i < N; i++){
            Arrays.fill(dp[i], -1);
        }


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0, 0));

    }


    private static boolean chkIdx(int x, int y) {
        if (0 <= x && x < N && 0 <= y && y < M) return true;
        return false;
    }

    private static int dfs(int x, int y) {

        if (x == N - 1 && y == M - 1) {
            return 1;
        }

        if(dp[x][y] != -1)
            // -1 이 아니면 이미 방문한 경우
            return dp[x][y];
        else {
            // 방문 안한 경우
            dp[x][y] = 0;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (chkIdx(nx, ny) && map[x][y] > map[nx][ny]) {
                    // 가능한 경로 개수 누적
                    dp[x][y] += dfs(nx, ny);
                }
            }
        }
        return dp[x][y];
    }



}