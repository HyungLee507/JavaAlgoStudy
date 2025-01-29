package bj_1405_미친로봇;

import java.io.*;
import java.util.*;

public class Main {

    static int N, R, L, D, U;
    static boolean[][] visit = new boolean[30][30];
    static double[] RLDU = new double[4];
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static double ans;

    static void backtrack(int x, int y, int step, double possibility){
        if(step == N){
            ans += possibility;
            return;
        }
        visit[x][y] = true;
        for(int d = 0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(visit[nx][ny]) continue;
            visit[nx][ny] = true;
            backtrack(nx, ny, step + 1, RLDU[d] * possibility);
            visit[nx][ny] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < 4; i++){
            RLDU[i] = Integer.parseInt(st.nextToken()) * 0.01;
        }

        int sx = 15,  sy = 15;

        // 단순할 확률 = N번 이동하는 동안 한 번 방문한 곳을 다시 방문하지 않을 확률
        for(int d = 0; d < 4; d++){
            backtrack(sx, sy, 0, RLDU[d]);
        }

        System.out.println(ans);
    }
}