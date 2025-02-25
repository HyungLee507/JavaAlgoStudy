package backjoon.study.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9944 {

    static int[][] board;
    static int N, M, result, limit;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int count = 1;

        while (true) {
            String line = bf.readLine();
            if (line == null || line.trim().isEmpty()) {
                break;
            }

            st = new StringTokenizer(line);
            if (st.countTokens() < 2) {
                continue;
            }

            // init
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new int[N][M];
            limit = 0;

            for (int i = 0; i < N; i++) {
                line = bf.readLine();
                if (line == null || line.trim().isEmpty()) {
                    break;
                }

                for (int j = 0; j < M; j++) {
                    if (line.charAt(j) == '*') {
                        board[i][j] = 1;
                    } else {
                        limit++;
                    }
                }
            }

            result = Integer.MAX_VALUE;
            visited = new boolean[N][M];
            backTracking();
            sb.append("Case ").append(count++).append(": ");
            if (limit == 1) {
                sb.append(0);
            } else {
                if (result == Integer.MAX_VALUE) {
                    sb.append(-1);
                } else {
                    sb.append(result);
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
        bf.close();
    }

    private static void backTracking() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        dfs(i, j, k, 1, 1);
                    }
                }
            }
        }
    }

    private static void dfs(int x, int y, int dir, int count, int curve) {
        if (curve >= result) {
            return;
        }
        if (count == limit) {
            result = Math.min(curve, result);
            return;
        }
        visited[x][y] = true;
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (nx >= 0 && nx < N && ny >= 0 && ny < M && board[nx][ny] == 0 && !visited[nx][ny]) {
            dfs(nx, ny, dir, count + 1, curve);
        } else {
            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && board[nx][ny] == 0 && !visited[nx][ny]) {
                    dfs(nx, ny, i, count + 1, curve + 1);
                }
            }
        }
        visited[x][y] = false;
    }

}
