package week5;

import java.io.*;
import java.util.*;

public class Main_b1719 {

    static class Node {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int n, m;
    static List<Node>[] graph;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for (int i=0; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }
        map = new int[n+1][n+1];

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[f].add(new Node(t,c));
            graph[t].add(new Node(f,c));
        }

        for (int i=1; i<=n; i++) {
            dijkstra(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (i == j) sb.append("-").append(" ");
                else sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void dijkstra(int start) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n+1];
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.cost-o2.cost);
        queue.offer(new Node(start, 0));
        dist[start] = 0;
        while(!queue.isEmpty()) {
            Node temp = queue.poll();
            int to = temp.to;
            int cost = temp.cost;
            visited[to] = true;
            for (Node node : graph[to]) {
                if (!visited[node.to] && dist[node.to] > node.cost + dist[to]) {
                    dist[node.to] = node.cost + dist[to];
                    map[node.to][start] = to;
                    queue.offer(new Node(node.to, dist[node.to]));
                }
            }
        }
    }
}
