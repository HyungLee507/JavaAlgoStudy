package backjoon.study.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_2665 {
    public static final int BLACK = 0;
    public static final int WHITE = 1;
    public static final int[] dx = {0, 0, 1, -1};
    public static final int[] dy = {1, -1, 0, 0};
    static int[][] map;
    static boolean[][] visited;
    static int N;

    static class Room implements Comparable<Room> {
        int x;
        int y;
        int cnt;

        public Room(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Room o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String temp = bf.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp.charAt(j) == '0' ? BLACK : WHITE;
            }
        }
        // 흰방을 지나가는건 가능 , 검은방을 지나가는건 cnt 를 높이는 방법
        System.out.println(execMinimumChange());
        bf.close();
    }

    private static int execMinimumChange() {
        int result = 0;
        PriorityQueue<Room> queue = new PriorityQueue<>();
        visited = new boolean[N][N];
        visited[0][0] = true;
        queue.offer(new Room(0, 0, 0));
        while (!queue.isEmpty()) {
            Room poll = queue.poll();
            if (poll.x == N - 1 && poll.y == N - 1) {
                result = poll.cnt;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if (map[nx][ny] == BLACK) {
                        queue.offer(new Room(nx, ny, poll.cnt + 1));
                    } else {
                        queue.offer(new Room(nx, ny, poll.cnt));
                    }
                }
            }
        }
        return result;
    }
}
