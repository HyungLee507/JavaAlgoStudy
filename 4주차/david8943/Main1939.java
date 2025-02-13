import java.io.*;
import java.util.*;

public class Main1939 {
    static int n, m, one, two;
    static List<int[]>[] bridges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        bridges = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            bridges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            bridges[a].add(new int[]{b, c});
            bridges[b].add(new int[]{a, c});
        }

        st = new StringTokenizer(br.readLine());
        one = Integer.parseInt(st.nextToken());
        two = Integer.parseInt(st.nextToken());

        int start = 1, end = 1_000_000_000, result = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (bfs(mid)) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(result);
    }

    static boolean bfs(int weight) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.add(one);
        visited[one] = true;

        while (!queue.isEmpty()) {
            int x = queue.poll();

            for (int[] bridge : bridges[x]) {
                int next = bridge[0];
                int w = bridge[1];

                if (!visited[next] && w >= weight) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        return visited[two];
    }
}