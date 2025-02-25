package backjoon.study.week1.HyungLee507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_1743 {
    public static final int TRASH = 1;
    public static final int[] dx = {1, -1, 0, 0};
    public static final int[] dy = {0, 0, 1, -1};
    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = TRASH;
        }
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == TRASH) {
                    result = Integer.max(bfs(i, j), result);
                }
            }
        }

        System.out.println(result);
        bf.close();
    }

    private static int bfs(int i, int j) {
        int cnt = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int k = 0; k < 4; k++) {
                int x = poll[0] + dx[k];
                int y = poll[1] + dy[k];
                if (x >= 0 && x < N && y >= 0 && y < M && !visited[x][y] && map[x][y] == TRASH) {
                    cnt++;
                    visited[x][y] = true;
                    queue.add(new int[]{x, y});
                }
            }
        }
        return cnt;
    }
}
