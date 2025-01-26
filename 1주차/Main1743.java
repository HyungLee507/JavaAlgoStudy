import java.util.*;
import java.io.*;

public class Main1743 {
    static boolean[][] arr;
    static boolean[][] v;
    static int[] dx = new int[] {-1,0,1,0};
    static int[] dy = new int[] {0,-1,0,1};
    static int result = 0;
    static int cnt = 0;
    static int n, m;

    static void dfs(int x, int y) {
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx>=0 && nx<n && ny>=0 && ny<m && arr[nx][ny] && !v[nx][ny]){
                v[nx][ny] = true;
                cnt++;
                dfs(nx, ny);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new boolean[n][m];
        v = new boolean[n][m];

        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x-1][y-1] = true;
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                cnt = 0;
                if(arr[i][j] && !v[i][j]){
                    dfs(i,j);
                }
                result = Math.max(result,cnt);
            }
        }
        System.out.println(result);
    }
}
