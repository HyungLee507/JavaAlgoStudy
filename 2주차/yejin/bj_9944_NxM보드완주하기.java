package bj_9944_NxM보드완주하기;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, empty, ans;
    static char[][] board;
    static boolean[][] visit;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    // filled = 지금까지 먹은 빈 칸 수, stage = 현재 단계
    static void backtrack(int x, int y, int filled, int stage){
        if(filled == empty){ // 빈 칸 다 방문했다!
            ans = Math.min(ans, stage);
            return;
        }
        for(int d = 0; d < 4; d++){
            int cnt = 0;
            int cx = x;
            int cy = y;
            while(true){
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                if(N <= nx || nx < 0 || M <= ny || ny < 0 || board[nx][ny] == '*' || visit[nx][ny]) break;
                cx = nx;
                cy = ny;
                visit[cx][cy] = true;
                cnt++;
            }
            if(cx == x && cy == y){ // while문을 돌고 나와도 위치가 변하지 않았다면 바로 다음 방향으로 진행
                continue;
            } else {
                backtrack(cx, cy, filled + cnt, stage + 1);
                for(int k = 0; k < cnt; k++){ // while문 내에서 한 방향으로 직진하면서 채운 빈 칸들 전부 원복
                    int mx = cx - (dx[d] * k);
                    int my = cy - (dy[d] * k);
                    visit[mx][my] = false;
                }

            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String tc;
        int caseCnt = 1;

        while((tc = br.readLine()) != null && !tc.isEmpty()){
            sb.append("Case ").append(caseCnt).append(": ");
            st = new StringTokenizer(tc);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new char[N][M];
            visit = new boolean[N][M];
            empty = 0;

            for(int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    char cur = line.charAt(j);
                    if(cur == '.') empty++;
                    board[i][j] = cur;
                }
            }
            ans = Integer.MAX_VALUE;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(board[i][j] == '.'){
                        visit[i][j] = true;
                        backtrack(i, j, 1, 0);
                        visit[i][j] = false;
                    }
                }
            }
            if(ans == Integer.MAX_VALUE){
                ans = -1;
            }
            sb.append(ans).append("\n");
            caseCnt++;
        }
        System.out.println(sb);
    }
}
