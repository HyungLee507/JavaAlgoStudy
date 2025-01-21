import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K, ans;
    static int[][] arr, spots;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static void bfs(int sx, int sy){
        int cnt = 0;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            cnt += 1;
            for(int d = 0; d < 4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(0 <= nx && nx < N && 0 <= ny && ny < M && arr[nx][ny] == 1 && !visited[nx][ny]){
                    q.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        if(ans < cnt){
            ans = cnt;
        }
    }

    //1. ㅆㄺ가 있는 좌표를 전부 저장
    //2. ㅆㄺ 좌표가 담긴 배열을 돌면서 해당 좌표에 방문하지 않았다면 그 좌표에서 bfs 시작
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];
        spots = new int[K][2]; // 쓰레기 좌표 저장

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            arr[X-1][Y-1] = 1;
            spots[i][0] = X-1;
            spots[i][1] = Y-1;
        }

        ans = -1;
        for(int i = 0; i < K; i++){
            int x = spots[i][0];
            int y = spots[i][1];
            if(visited[x][y]) continue;
            bfs(x, y);
        }

        System.out.println(ans);
    }
}