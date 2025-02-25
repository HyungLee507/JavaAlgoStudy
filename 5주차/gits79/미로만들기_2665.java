import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, map[][], dist[][];
    static final int di[] = {-1, 0, 1, 0};
    static final int dj[] = {0, 1, 0, -1};


    static int bfs() {
        // 우선순위 큐 오름차순 정렬
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });
        // x, y, cnt(검은색을 흰색으로 바꾼 수)
        q.offer(new int[]{0, 0, 0});
        // 시작점 초기화
        dist[0][0] = 0;

        while (!q.isEmpty()) {
            int[] ijc = q.poll();
            int i = ijc[0];
            int j = ijc[1];
            int cnt = ijc[2];
            // 도착점 도달시 최소 개수 꺼냄
            if (i == N - 1 && j == N - 1) {
                return dist[i][j];
            }

            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];
                if (chkIdx(ni, nj)) {
                    int nextCnt = cnt;
                    // 검은색 방으 바꿔야 한다면 갯수 증가
                    if (map[ni][nj] == 0) nextCnt++;

                    // 바꾼 수가 최단 갯수 보다 적어야 갱신 후
                    // 큐에 넣기
                    if (nextCnt < dist[ni][nj]) {
                        dist[ni][nj] = nextCnt;
                        q.offer(new int[]{ni, nj, nextCnt});
                    }

                }
            }
        }
        return -1;
    }

    static boolean chkIdx(int i, int j) {
        if (0 <= i && i < N && 0 <= j && j < N) return true;
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dist = new int[N][N];

        for (int[] a : dist) Arrays.fill(a, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }
}
