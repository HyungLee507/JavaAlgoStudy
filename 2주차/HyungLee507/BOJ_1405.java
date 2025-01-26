package backjoon.study.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1405 {
    static double result = 0;
    static int N;
    static double[] percent;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        percent = new double[4];
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            percent[i] = Double.parseDouble(st.nextToken()) / 100;
        }
        visited = new boolean[30][30];
        dfs(0, 15, 15, 1.0);
        System.out.println(result);
        bf.close();
    }

    private static void dfs(int depth, int x, int y, double probability) {
        if (depth == N) {
            result += probability;
            return;
        }
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < 30 && ny < 30 && !visited[nx][ny]) {
                dfs(depth + 1, nx, ny, probability * percent[i]);
            }
        }
        visited[x][y] = false;
    }
}
