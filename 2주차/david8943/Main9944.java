import java.io.*;
import java.util.StringTokenizer;

public class Main9944 {
    static int n, m;
    static boolean[][] map;
    static boolean[][] v;
    static int blank;
    static int result;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    static void backTracking(int x, int y, int dir, int cnt, int phase) {
        // 모든 칸을 방문했을 경우 결과 갱신
        if (cnt == blank) {
            result = Math.min(result, phase);
            return;
        }
        // 현재 phase가 이미 result보다 크면 종료
        if (phase >= result) {
            return;
        }

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        // 현재 방향으로 이동할 수 없는 경우
        if (nx < 0 || ny < 0 || nx >= n || ny >= m || !map[nx][ny] || v[nx][ny]) {
            // 모든 방향으로 회전 시도
            for (int i = 0; i < 4; i++) {
                int nextDir = (dir + i) % 4;
                nx = x + dx[nextDir];
                ny = y + dy[nextDir];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && map[nx][ny] && !v[nx][ny]) {
                    v[nx][ny] = true;
                    backTracking(nx, ny, nextDir, cnt + 1, phase + 1);
                    v[nx][ny] = false;
                }
            }
        } else {
            // 이동 가능한 경우
            v[nx][ny] = true;
            backTracking(nx, ny, dir, cnt + 1, phase);
            v[nx][ny] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String line;
        int t = 1;

        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new boolean[n][m];
            v = new boolean[n][m];
            result = Integer.MAX_VALUE;
            blank = 0;

            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < m; j++) {
                    if (s.charAt(j) == '.') {
                        map[i][j] = true; // 빈 칸 설정
                        blank++;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j]) { // 빈 칸에서 시작
                        v[i][j] = true;
                        for (int d = 0; d < 4; d++) {
                            backTracking(i, j, d, 1, 1);
                        }
                        v[i][j] = false;
                    }
                }
            }
            if (blank == 1) {
                System.out.println("Case " + t + ": 0");
            }
            else if (result == Integer.MAX_VALUE) {
                System.out.println("Case " + t + ": -1");
            } else {
                System.out.println("Case " + t + ": " + result);
            }
            t++;
        }
    }
}
