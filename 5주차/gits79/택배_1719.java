import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }

    }

    static int N, M, dist[];
    static String map[][];
    static boolean[] v;
    static ArrayList<ArrayList<Node>> graph;


    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        v = new boolean[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (v[cur.vertex]) continue;
            v[cur.vertex] = true;

            for (Node next : graph.get(cur.vertex)) {
                // 최단 거리가 갱신되는 경우
                if (dist[next.vertex] > dist[cur.vertex] + next.weight) {
                    dist[next.vertex] = dist[cur.vertex] + next.weight;
                    pq.offer(new Node(next.vertex, dist[next.vertex]));
                    // 출발점에서 다음 노드로 가는 최단 경로에서 첫 번째 방문 노드를 저장
                    // 더 짧은 경로를 찾을 때마다 map 업데이트
                    map[next.vertex][start] = "" + cur.vertex;
                }
            }

        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new String[N + 1][N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(to).add(new Node(from, weight));
            graph.get(from).add(new Node(to, weight));
        }


        for (int i = 1; i <= N; i++) {
            dijkstra(i);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == null) {
                    System.out.print("- ");
                } else System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }
}
