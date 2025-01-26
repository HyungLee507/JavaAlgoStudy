import java.io.*;
import java.util.*;

public class Main16988 {
    static int n, m;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int result = 0;

    static void placeStones(int x1, int y1, int x2, int y2) {
        map[x1][y1] = 1;
        map[x2][y2] = 1;

        boolean[][] v = new boolean[n][m];
        int temp_result = 0;

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(map[i][j]==2 && !v[i][j]) {
                    int groupSize = checkGroup(i,j,v);
                    if(groupSize>0) {
                        temp_result += groupSize;
                    }
                }
            }
        }
        result = Math.max(temp_result,result);

        map[x1][y1] = 0;
        map[x2][y2] = 0;
    }

    static int checkGroup(int x, int y, boolean[][] v) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x,y});
        v[x][y] = true;
        int size = 0;
        boolean isCaptured = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            size++;

            for(int i=0;i<4;i++) {
                int nx = curr[0]+dx[i];
                int ny = curr[1]+dy[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;

                if(map[nx][ny]==0) {
                    isCaptured = false;
                } else if(map[nx][ny]==2 && !v[nx][ny]) {
                    v[nx][ny] = true;
                    q.offer(new int[]{nx,ny});
                }
            }
        }
        return isCaptured ? size : 0;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        List<int[]> emptyCells = new ArrayList<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) emptyCells.add(new int[]{i,j});
            }
        }

        for(int i = 0; i < emptyCells.size(); i++){
            for(int j = i+1; j < emptyCells.size(); j++){
                int[] cell1 = emptyCells.get(i);
                int[] cell2 = emptyCells.get(j);
                placeStones(cell1[0], cell1[1], cell2[0], cell2[1]);
            }
        }

        System.out.println(result);
    }
}
