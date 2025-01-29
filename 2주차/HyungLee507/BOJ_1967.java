package backjoon.study.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1967 {

    static int N, distance, startNode;
    static List<Edge>[] edges;
    static boolean[] used;

    private static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(o.weight, this.weight);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(bf.readLine());
        edges = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        used = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[start].add(new Edge(end, weight));
            edges[end].add(new Edge(start, weight));
        }
//        for (int i = 0; i <= N; i++) {
//            Collections.sort(edges[i]);
//        }
        distance = 0;
        used = new boolean[N + 1];
        dfs(1, 0);
        used = new boolean[N + 1];
        dfs(startNode, 0);
        System.out.println(distance);
        bf.close();
    }

    private static void dfs(int start, int dist) {
        used[start] = true;
        for (int i = 0; i < edges[start].size(); i++) {
            Edge edge = edges[start].get(i);
            if (!used[edge.to]) {
                dfs(edge.to, dist + edge.weight);
            }
        }
        if (dist > distance) {
            startNode = start;
            distance = dist;
        }
    }
}
