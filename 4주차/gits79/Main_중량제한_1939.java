package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


public class Main_중량제한_1939 {

    static class Node {
        int vertex;
        int weight;
        Node next;

        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }


    }

    static Node[] g;
    static boolean[] v;

    static boolean bfs(int start, int end, int mid) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        v[start] = true;
        // 시작점 넣음
        q.add(start);

        while (!q.isEmpty()) {
            int vertex = q.poll();

            if (vertex == end) {
//                System.out.println("mid: " + mid);
                return true;
            }

            for (Node j = g[vertex]; j != null; j = j.next) {
                // 최대 중량을 버틸 수 있는 다리만 이동
                if (!v[j.vertex] && j.weight >= mid) {
                    v[j.vertex] = true;
                    q.add(j.vertex);
                }
            }
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        g = new Node[N + 1];
        v = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            g[from] = new Node(to, weight, g[from]);
            g[to] = new Node(from, weight, g[to]);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());


        int left = 0;
        int right = Integer.MAX_VALUE;

        while (left + 1 < right) {
            int mid = (left + right) / 2;

            // 최대 중량을 미드로 정하고 성공한다면 늘려서 찾아 나감
            if (bfs(start, end, mid)) left = mid;
            else right = mid;
            v = new boolean[N + 1];

        }
        System.out.println(left);


    }
}
