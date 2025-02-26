import java.io.*;
import java.util.*;

public class Main2665 {
    static int n;
    static int[][] room;
    static int[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // Initialize room and visit arrays
        room = new int[n][n];
        visit = new int[n][n];

        // Read room data
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                room[i][j] = line.charAt(j) - '0';
            }
        }

        dijkstra();
    }

    static void dijkstra() {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // PriorityQueue for Dijkstra's algorithm
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, 0}); // {distance, x, y}
        visit[0][0] = 1;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int a = current[0];
            int x = current[1];
            int y = current[2];

            if (x == n - 1 && y == n - 1) {
                System.out.println(a);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && visit[nx][ny] == 0) {
                    visit[nx][ny] = 1;
                    if (room[nx][ny] == 0) {
                        pq.offer(new int[]{a + 1, nx, ny});
                    } else {
                        pq.offer(new int[]{a, nx, ny});
                    }
                }
            }
        }
    }
}