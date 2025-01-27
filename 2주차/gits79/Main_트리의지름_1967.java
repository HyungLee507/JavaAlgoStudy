import java.io.*;
import java.util.*;

public class Main {
		// 노드 클래스 작성
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

    static int N, answer;
    static Node[] g;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        g = new Node[N + 1];
        v = new boolean[N + 1];


        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
						// 무방향 그래프이기때문에 양방향 연결
            g[from] = new Node(to, weight, g[from]);
            g[to] = new Node(from, weight, g[to]);

        }


        answer = 0;
        // 모든 노드에 대해서 dfs
        for (int i = 1; i <= N; i++) {
            v = new boolean[N + 1];
            dfs(i, 0, 0);
        }
        System.out.println(answer);


    }

    static void dfs(int start, int weight, int depth) {
        v[start] = true;
        // 그래서 가중치 합이 가장 큰 것 찾아냄
        answer = Math.max(answer, weight);
        
        // 해당노드에 연결되어 있는 노드를 탐색
        // depth는 확인용 이 문제에서는 필요x
        // 해당 노드에서 다른 노드로 이동시 가중치 더해줌
        for (Node j = g[start]; j != null; j = j.link) {
            if (!v[j.vertex]) {
                dfs(j.vertex, weight + j.weight, depth + 1);
            }
        }
    }

}
