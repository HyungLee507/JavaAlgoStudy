import java.util.*;
import java.io.*;

public class Main {

    static int N, M, ans;
    static int[][] board, result, picked;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visit;
    static List<int[]> empty, other;

    static void comb(int idx, int cnt){
        if(cnt == 2){
            int max = getMaxkill();
            if(ans < max){
                ans = max;
            }
            return;
        }
        for(int i = idx; i < empty.size(); i++){
            picked[cnt][0] = empty.get(i)[0];
            picked[cnt][1] = empty.get(i)[1];
            comb(i + 1, cnt + 1);
        }
    }

    static int getMaxkill(){
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                result[i][j] = board[i][j];
            }
        }
        result[picked[0][0]][picked[0][1]] = 1; result[picked[1][0]][picked[1][1]] = 1;
        visit = new boolean[N][M];
        for (int[] o : other) {
            int x = o[0];
            int y = o[1];
            if (visit[x][y]) continue;
            int size = BFS(x, y);
            if(size > 0){
                cnt += size;
            }
        }
        return cnt;
    }

    static int BFS(int sx, int sy){
        int size = 0;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {sx, sy});
        visit[sx][sy] = true;
        boolean canKill = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            size++;
            for(int d = 0; d < 4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(0 <= nx && nx < N && 0 <= ny && ny < M && !visit[nx][ny] && result[nx][ny] != 1){
                    if(result[nx][ny] == 0) canKill = false;
                    q.offer(new int[] {nx, ny});
                    visit[nx][ny] = true;
                }
            }
        }
        return canKill ? size : -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        result = new int[N][M]; // 돌 두개 놓은 후의 보드 카피할 배열
        picked = new int[2][2]; // 내가 뽑은 두 자리
        visit = new boolean[N][M]; // 상대 돌로 BFS 돌릴 때 사용할 방문 배열
        empty = new ArrayList<>(); // board[value/M][value%M]
        other = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                int cur = Integer.parseInt(st.nextToken());
                board[i][j] = cur;
                if(cur == 0) {
                    empty.add(new int[]{i, j});
                } else if (cur == 2){
                    other.add(new int[]{i, j});
                }
            }
        }

        ans = 0;
        comb(0, 0);
        System.out.println(ans);
    }
}
