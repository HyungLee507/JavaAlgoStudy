package ziy00n;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1405 {
    static int[] dy = {0,0,1,-1}; // 동서남북
    static int[] dx = {1,-1,0,0};
    static boolean[][] visited;
    static double[] per;
    static int N;
    static double ans;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb= new StringBuilder();

        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());

        per = new double[4];
        for(int i = 0; i < 4; i++) {
            per[i] = Integer.parseInt(st.nextToken()) * 0.01;
        }

        ans = 0;

        visited = new boolean[40][40];
        visited[20][20] = true;

        dfs(20,20, 0, 1);

        System.out.println(ans);
        br.close();
    }

    static void dfs(int sr, int sc, int depth, double total) {
        if(depth == N) {
            ans += total;
            return;
        }

        for(int i = 0; i < 4; i++) {
            int rr = sr + dy[i];
            int cc = sc + dx[i];

            if(0 > rr || rr >= 40 || 0 > cc || cc >= 40) continue;
            if(visited[rr][cc]) continue;
            visited[rr][cc] = true;
            dfs(rr, cc, depth + 1, total * per[i]);
            visited[rr][cc] = false;
        }
    }
}
