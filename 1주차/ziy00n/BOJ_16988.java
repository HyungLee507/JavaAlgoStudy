package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16988 {

    static int N, M, ans;
    static int[][] board;
    static int[] selected;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int convert2Dto1D(int r, int c) {
        return r * M + c;
    }

    static int[] convert1Dto2D(int pos) {
        return new int[]{pos / M, pos % M};
    }

    static void combination(int depth, int start) {
        if (depth == 2) {
            ans = Math.max(ans, solution());
            return;
        }

        for (int i = start; i < N * M; i++) {
            int[] p = convert1Dto2D(i);
            if (board[p[0]][p[1]] == 0) {
                selected[depth] = i;
                combination(depth + 1, i + 1);
            }
        }
    }

    static int solution() {
        int result = 0;
        int[][] cpyBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cpyBoard[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < 2; i++) {
            int[] p = convert1Dto2D(selected[i]);
            cpyBoard[p[0]][p[1]] = 1;
        }

        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (cpyBoard[i][j] == 2) {
                    result += bfs(i, j, cpyBoard, visited);
                }
            }
        }

        return result;
    }

    static int bfs(int sr, int sc, int[][] cpyBoard, boolean[][] visited) {
        int result = 0;
        boolean flag = false;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            result += 1;

            for (int i = 0; i < 4; i++) {
                int rr = cur[0] + dy[i];
                int cc = cur[1] + dx[i];

                if (rr < 0 || rr >= N || cc < 0 || cc >= M) {
                    continue;
                }
                if (visited[rr][cc]) {
                    continue;
                }
                if (cpyBoard[rr][cc] == 1) {
                    continue;
                }
                if (cpyBoard[rr][cc] == 0) {
                    flag = true;
                    continue;
                }
                q.offer(new int[]{rr, cc});
                visited[rr][cc] = true;
            }
        }
        return flag ? 0 : result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        selected = new int[2];
        ans = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0);
        System.out.println(ans);
    }
}