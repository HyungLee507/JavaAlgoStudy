package bj_1719_택배;

import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node>{
        int to;
        int cost;

        Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N, M;
    static List<Node>[] graph;
    static int[][] results;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        results = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        for(int i = 1; i <= N; i++){
            dijkstra(i);
        }

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                String s = results[i][j] == 0 ? "-" : results[i][j] + "";
                sb.append(s + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    static void dijkstra(int from){
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[N+1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[from] = 0;
        pq.offer(new Node(from, 0));
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(node.cost > dist[node.to]){
                continue;
            }
            visited[node.to] = true;
            for(Node next : graph[node.to]){
                if(!visited[next.to] && node.cost + next.cost < dist[next.to]){
                    dist[next.to] = node.cost + next.cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                    results[next.to][from] = node.to;
                }
            }
        }
    }
}
