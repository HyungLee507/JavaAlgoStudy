package backjoon.study.week1.HyungLee507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16988 {

    public static final int BLACK = 2;
    public static final int WHITE = 1;
    public static final int BLANK = 0;
    public static final int[] dx = {1, -1, 0, 0};
    public static final int[] dy = {0, 0, 1, -1};
    static int N, M, result;
    static boolean[][] visited;
    static int[][] board;
    static List<int[]> candidates;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        result = 0;
        candidates = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                int temp = Integer.parseInt(st.nextToken());
                board[i][j] = temp;
                if (temp == BLANK) {
                    candidates.add(new int[]{i, j});
                }
            }
        }
        // 후보군에서 2개 뽑아서 bfs 돌림.
        dfs(0, 0, new LinkedList<>());
        System.out.println(result);
        bf.close();
    }

    private static void dfs(int idx, int cnt, LinkedList<int[]> placeLoc) {
        if (cnt == 2) {
            catchAllBlack(placeLoc);
            return;
        }
        if (idx == candidates.size()) {
            return;
        }
        placeLoc.add(candidates.get(idx));
        dfs(idx + 1, cnt + 1, placeLoc);
        placeLoc.removeLast();
        dfs(idx + 1, cnt, placeLoc);
    }

    private static void catchAllBlack(LinkedList<int[]> placeLoc) {
        visited = new boolean[N][M];
        for (int[] ints : placeLoc) {
            board[ints[0]][ints[1]] = WHITE;
        }
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == BLACK && !visited[i][j]) {
                    total += bfs(i, j);
                }
            }
        }
        result = Integer.max(result, total);
        for (int[] ints : placeLoc) {
            board[ints[0]][ints[1]] = BLANK;
        }
    }

    private static int bfs(int stx, int sty) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{stx, sty});
        visited[stx][sty] = true;
        int cnt = 1;
        boolean flag = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = poll[0] + dx[i];
                int nextY = poll[1] + dy[i];
                if (nextY >= 0 && nextX >= 0 && nextX < N && nextY < M && !visited[nextX][nextY]) {
                    if (board[nextX][nextY] == BLANK) {
                        flag = false;
                    } else if (board[nextX][nextY] == BLACK) {
                        queue.add(new int[]{nextX, nextY});
                        cnt++;
                        visited[nextX][nextY] = true;
                    }
                }
            }
        }
        if (flag) {
            return cnt;
        } else {
            return 0;
        }
    }
}
