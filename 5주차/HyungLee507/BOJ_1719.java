package backjoon.study.week5.HyungLee507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1719 {
    static int N, M;
    static ArrayList<Node>[] adjustNodes;
    static int[][] result;
    static final int INF = 1000000000;

    static class Node implements Comparable<Node> {
        int end, cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjustNodes = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjustNodes[i] = new ArrayList<>();
        }

        result = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(result[i], -1);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            adjustNodes[a].add(new Node(b, cost));
            adjustNodes[b].add(new Node(a, cost));
        }

        for (int s = 0; s < N; s++) {
            dijkstra(s);
        }
        printResult();
        bf.close();
    }

    private static void dijkstra(int start) {
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        int[] nextLocation = new int[N];
        Arrays.fill(nextLocation, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int now = cur.end;
            if (cur.cost > dist[now]) {
                continue;
            }

            for (Node next : adjustNodes[now]) {
                int nextNode = next.end;
                int newCost = cur.cost + next.cost;
                // 이동하려는 놈의 거리보다 현재 더한 거리가 작으면 최신화!!
                if (newCost < dist[nextNode]) {
                    dist[nextNode] = newCost;
                    // 거리 말고 이전 녀석의 위치를 알고 있는거임!!
                    if (now == start) {
                        nextLocation[nextNode] = nextNode;
                    } else {
                        nextLocation[nextNode] = nextLocation[now];
                    }
                    pq.offer(new Node(nextNode, newCost));
                }
            }
        }
        // 위치 박아주기
        for (int i = 0; i < N; i++) {
            if (i == start) {
                continue;
            }
            result[start][i] = nextLocation[i];
        }
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    sb.append("- ");
                } else {
                    sb.append(result[i][j] + 1).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
