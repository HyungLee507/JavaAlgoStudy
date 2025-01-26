package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1240 {

    static int N, M;
    static List<int[]>[] graph;
    static int s,e,ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N+1];

        for(int i = 0; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new int[] {e,w});
            graph[e].add(new int[] {s,w});
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            ans = 0;
            bfs();
            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }


    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        q.offer(new int[]{s, 0});

        while(!q.isEmpty()) {
            int[] node = q.poll();
            int v = node[0];
            int d = node[1];
            if(v == e) {
                ans = d;
                break;
            }

            for(int i = 0; i < graph[v].size(); i++) {
                int[] next = graph[v].get(i);
                int nv = next[0];
                int nd = next[1];

                if(visited[nv]) continue;
                q.offer(new int[]{nv, d + nd});
                visited[nv] = true;
            }
        }
    }

}
