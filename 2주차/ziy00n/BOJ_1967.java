package ziy00n;

import java.io.*;
import java.util.*;

public class BOJ_1967 {
    static int N;
    static List<Node>[] graph;
    static boolean[] visited;
    static int[] ans = new int[]{-1, -1}; // 거리, 노드번호
    static class Node {
        int node;
        int dist;

        public Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine()); // 노드의 개수

        graph = new List[N+1];
        for(int i = 0; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[parent].add(new Node(child, weight));
            graph[child].add(new Node(parent, weight));
        }

        // 첫 번째 DFS 시작 (루트 노드부터 시작)
        visited = new boolean[N+1];
        visited[1] = true;
        dfs(1, 0); // 루트노드1, 거리 0

        // 루트노드부터 가장 멀리 떨어진 노드 번호
        int sn = ans[1];

        ans = new int[] {-1, -1}; // ans 배열 초기화

        // 두 번째 DFS 시작 (가장 멀리 있는 노드 sn에서 시작)
        visited = new boolean[N+1];
        visited[sn] = true;
        dfs(sn, 0);

        System.out.println(ans[0]);
        br.close();
    }


    static void dfs(int snode, int total) {
        // TODO: 현재까지 온 노드의 거리가 가장 긴 경우 ans 변경
        if(ans[0] < total) {
            ans = new int[]{total, snode};// 가장 멀리 간 거리, 노드 번호
        }

        // 현재 노드의 자식 노드들에 대해 DFS 진행
        for(Node node : graph[snode]) {
            int child = node.node;
            int weight = node.dist;

            if(visited[child]) continue;
            visited[child] = true; // 방문처리

            dfs(child, total + weight);

            visited[child] = false; // 백트래킹 원복
        }
    }

}

