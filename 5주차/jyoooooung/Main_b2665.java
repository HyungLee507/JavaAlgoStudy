package week5;

import java.io.*;
import java.util.*;

public class Main_b2665 {
    static int n;
    static int[][] room;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        room = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                room[i][j] = line.charAt(j) - '0';
            }
        }

        for (int[] a : room) System.out.println(Arrays.toString(a));
        System.out.println();
        System.out.println(miro(room));
    }


    static int miro(int[][] map) {
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
        boolean[][] visited = new boolean[n][n];
        queue.offer(new int[]{0, 0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];
            int c = temp[2];
            visited[x][y] = true;
            if (x == n - 1 && y == n - 1) {
                return c;
            }
            for (int t = 0; t < 4; t++) {
                int xi = temp[0] + dx[t];
                int yi = temp[1] + dy[t];
                if (0 <= xi && xi < n && 0 <= yi && yi < n && !visited[xi][yi]) {
                    if (map[xi][yi] == 0) {
                        queue.offer(new int[]{xi, yi, c + 1});
                    } else {
                        queue.offer(new int[]{xi, yi, c});
                    }
                    visited[xi][yi] = true;
                }
            }
        }
        return 0;
    }
}
