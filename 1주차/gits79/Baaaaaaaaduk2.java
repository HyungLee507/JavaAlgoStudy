import java.io.*;
import java.util.*;

public class Main {

    static int N, M, map[][];
    static boolean[][] v;

    static final int[] di = {-1, 0, 1, 0};
    static final int[] dj = {0, 1, 0, -1};


    static int bfs(int i, int j) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{i, j});
        v[i][j] = true;
        int cnt = 1;
        // 흑돌이 죽는지 여부 확인
        boolean flag = false;

        while (!q.isEmpty()) {
            int[] ij = q.poll();
            i = ij[0];
            j = ij[1];

            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];
                // 흑돌은 한칸이라도 빈칸이 있으면 죽지 않음
                if (0 <= ni && ni < N && 0 <= nj && nj < M && map[ni][nj] == 0) flag = true;
                // 다른 흑돌을 만나면 이동
                if (0 <= ni && ni < N && 0 <= nj && nj < M && map[ni][nj] == 2 && !v[ni][nj]) {
                    q.offer(new int[]{ni, nj});
                    v[ni][nj] = true;
                    cnt++;
                }
            }
        }
        if (flag) return 0;
        return cnt;
    }


    static int sum() {
        int cnt = 0;
        v = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2 && !v[i][j]) cnt += bfs(i, j);
            }
        }
        return cnt;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
            // 완탐으로 임의의 첫번째점 선택
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < M; y++) {
		                    // 임의의 두번째점 선택
		                    // 두개가 같다면 패스
                        if (x == i && y == j) continue;
                        if (map[x][y] == 0 && map[i][j] == 0) {
                            map[x][y] = 1;
                            map[i][j] = 1;
                            answer = Math.max(answer, sum());
                            // 흰돌 놓았던 것을 원상복구
                            map[x][y] = 0;
                            map[i][j] = 0;
                        }
                    }
                }
            }
        }

        System.out.println(answer);

    }
}