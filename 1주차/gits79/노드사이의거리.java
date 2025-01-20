import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int vertex;
        int weight;
        Node link;

        Node(int vertex, int weight, Node link) {
            this.vertex = vertex;
            this.weight = weight;
            this.link = link;
        }
    }

    static int N, M;
    static Node[] g;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        g = new Node[N + 1];
        v = new boolean[N + 1];


        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
						// 노드는 양방향으로 연결되어 있음
            g[from] = new Node(to, weight, g[from]);
            g[to] = new Node(from, weight, g[to]);

        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            bfs(from, to);
            v = new boolean[N + 1];
        }


    }

    static void bfs(int start, int end) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        v[start] = true;
        q.offer(new int[]{start, 0});  // 시작 노드 추가, 거리는 0

        while (!q.isEmpty()) {
            int[] cur = q.poll(); // 정점과 가중치 꺼냄  
            int vertex = cur[0]; 
            int weight = cur[1];

            if (vertex == end) {
                System.out.println(weight); // 도착 정점에 도달하면 가중치 출력
                return;
            }
						// 정점과 연결된 정점이 있다면 다음 정점으로 이동
            for (Node j = g[vertex]; j != null; j = j.link) {
                if (!v[j.vertex]) {
                    v[j.vertex] = true;
                    // 방문 여부 체크후 가중치 더함
                    q.offer(new int[]{j.vertex, weight + j.weight});
                }
            }
        }
    }

}
