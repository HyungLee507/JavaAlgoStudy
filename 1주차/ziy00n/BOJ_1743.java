package BOJ;

import java.io.*;
import java.util.*;
public class BOJ_1743 {
    static int N, M, K;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N+1][M+1];

        visited = new boolean[N+1][M+1];
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[r-1][c-1] = 1;

        }


        int ans = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(visited[i][j]) continue;
                if(graph[i][j] == 0) continue;
                int cnt = bfs(i,j);
                if(cnt > ans) {
                    ans = cnt;
                }
            }
        }

        System.out.println(ans);
        br.close();
    }

    static int bfs(int r, int c) {

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r,c});
        visited[r][c] = true;

        int cnt = 0;
        while(!q.isEmpty()) {
            int[] node =  q.poll();
            int qr = node[0];
            int qc = node[1];
            cnt++;

            for(int d = 0; d < 4; d++) {
                int rr = qr + dy[d];
                int cc = qc + dx[d];

                if(0 > rr || rr >= N || 0 > cc || cc >= M) continue;
                if(visited[rr][cc]) continue;
                if(graph[rr][cc] == 0) continue;
                q.offer(new int[] {rr,cc});
                visited[rr][cc] = true;
            }
        }

        return cnt;
    }
}
