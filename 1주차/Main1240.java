import java.util.*;
import java.io.*;

public class Main1240 {
    static int n;
    static List<int[]>[] graph; // 인접 리스트
    static boolean[] visited;

    static int bfs(int start, int aim) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { start, 0 });
        visited = new boolean[n + 1]; // 방문 배열 초기화
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int dist = curr[1];

            if (node == aim) return dist; // 목표 노드 도달 시 거리 반환

            for (int[] neighbor : graph[node]) {
                int next = neighbor[0];
                int weight = neighbor[1];

                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[] { next, dist + weight });
                }
            }
        }

        return -1; // (정상적인 입력에서는 발생하지 않음)
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            graph[x].add(new int[] { y, dist });
            graph[y].add(new int[] { x, dist });
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            sb.append(bfs(x, y)).append("\n");
        }

        System.out.println(sb.toString());
    }
}
