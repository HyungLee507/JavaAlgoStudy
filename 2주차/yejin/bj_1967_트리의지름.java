package bj_1967_트리의지름;

import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int dest;      // 연결된 노드
        int weight;  // 가중치

        Node(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    static int N; // 노드 개수
    static ArrayList<Node>[] nodes; // 인접 리스트
    static boolean[] visit; // 방문 체크 배열
    static int max, dist; // 최대 거리와 가장 먼 노드

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        if (N == 1) { // 노드가 하나일 경우
            System.out.println(0);
            return;
        }

        // 인접 리스트 초기화
        nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodes[from].add(new Node(to, weight));
            nodes[to].add(new Node(from, weight));
        }

        // 첫 번째 DFS: 임의의 노드(1번)에서 가장 먼 노드 찾기
        visit = new boolean[N + 1];
        dfs(1, 0);

        // 두 번째 DFS: 가장 먼 노드에서 다시 가장 먼 노드 찾기
        Arrays.fill(visit, false);
        max = 0;
        dfs(dist, 0);

        System.out.println(max);
    }

    // DFS 탐색 함수
    static void dfs(int current, int sum) {
        visit[current] = true;

        if (sum > max) { // 최대 거리 갱신
            max = sum;
            dist = current;
        }

        for (Node next : nodes[current]) {
            if (!visit[next.dest]) {
                dfs(next.dest, sum + next.weight);
            }
        }
    }
}
