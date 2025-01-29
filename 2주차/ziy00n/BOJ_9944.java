package ziy00n;

import java.io.*;
import java.util.*;

public class BOJ_9944 {
    static int N, M;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,1,-1};
    static char[][] board;
    static int ans;
    static boolean[][] visited;
    static int blankCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int tc = 0;
        while(true) {
            tc++;
            String input = br.readLine();
            if(input == null || input.trim().isEmpty()) {
                break;
            }

            st = new StringTokenizer(input, " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            board = new char[N][M];
            visited = new boolean[N][M];

            blankCnt = 0;

            for(int i = 0; i < N; i++) {
                String line = br.readLine();
                for(int j = 0; j < M; j++) {
                    board[i][j] = line.charAt(j);
                    if(board[i][j] == '.') {
                        blankCnt++;
                    }
                }
            }

            ans = Integer.MAX_VALUE;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(board[i][j] == '.') {
                        for(int d = 0; d < 4; d++) {
                            dfs(i, j, d,1, 1);
                        }
                    }
                }
            }

            sb.append("Case ").append(tc).append(": ");
            if(blankCnt == 1) { // 빈칸 1칸 일 때 이동횟수==0
                sb.append(0);
            } else{
                if(ans == Integer.MAX_VALUE) {
                    sb.append(-1);
                } else {
                    sb.append(ans);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    // 행, 열, 방향, 이동횟수, 이동한 칸 개수
    static void dfs(int sr, int sc, int dir, int cnt, int moveCnt) {
        if(moveCnt == blankCnt) { // 이동한 칸 수 == 빈칸개수면 종료
            ans = Math.min(cnt, ans);
            return;
        }

        visited[sr][sc] = true;
        int rr = sr + dy[dir];
        int cc = sc + dx[dir];

        if(isValid(rr,cc)) { // 이 방향으로 계속
            dfs(rr, cc, dir, cnt, moveCnt+1);
        }
        else { // 방향 변경
            for(int d = 0; d < 4; d++) {
                if(d == dir) continue;

                int rrr = sr + dy[d];
                int ccc = sc + dx[d];

                if(!isValid(rrr, ccc)) continue;
                dfs(rrr, ccc, d, cnt+1, moveCnt+1);
            }
        }
        visited[sr][sc] = false;

    }

    static boolean isValid(int rr, int cc) {
        if(0 > rr || rr >= N || 0 > cc || cc >= M) return false;
        if(visited[rr][cc] || board[rr][cc] == '*') return false;
        return true;
    }
}
