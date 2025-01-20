package backjoon.study.week1.HyungLee507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1240 {
    static int N, M;
    static List<Integer>[] adjustNodes;
    static int[][] dist;

    private static class Node {
        int idx;
        int distance;

        public Node(int idx, int distance) {
            this.distance = distance;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[N + 1][N + 1];
        adjustNodes = new List[N + 1];
        //init
        for (int i = 0; i <= N; i++) {
            adjustNodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            adjustNodes[start].add(end);
            adjustNodes[end].add(start);
            dist[start][end] = distance;
            dist[end][start] = distance;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(getTotalDist(start, end)).append('\n');
        }
        System.out.println(sb);
        bf.close();
    }

    private static int getTotalDist(int start, int end) {
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (poll.idx == end) {
                return poll.distance;
            }
            for (int next : adjustNodes[poll.idx]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new Node(next, poll.distance + dist[poll.idx][next]));
                }
            }
        }
        return 0;
    }
}

