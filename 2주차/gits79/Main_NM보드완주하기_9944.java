package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_NM보드완주하기_9944 {
    static int arr[][], n, m, min_result;
    static boolean visited[][];
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int num = 1;
        String input="";
        while ((input = bf.readLine())!= null) {
            st=new StringTokenizer(input);
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            arr = new int[n][m];
            visited = new boolean[n][m];
            min_result = -1;
            int result = 1;
            int cnt = 1;

            for (int i = 0; i < n; i++) {
                String str = bf.readLine();
                for (int j = 0; j < m; j++) {
                    char c = str.charAt(j);
                    arr[i][j] = c;
                    if (c == '*') {
                        cnt += 1;
                        visited[i][j] = true;
                    }
                }
            }

            if(cnt== n*m) min_result=0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j]) {
                        for (int k = 0; k < 4; k++) {
                            if(i+dx[k]>=0 && i+dx[k]<n && j+dy[k]>=0 && j+dy[k]<m && !visited[i+dx[k]][j+dy[k]]) {
                                visited[i][j] = true;
                                search(i, j, result, k, cnt);
                                visited[i][j] = false;
                            }
                        }
                    }
                }
            }
            System.out.println("Case " + num + ": " + min_result);
            num += 1;
        }
    }

    private static void search(int i, int j, int result, int direct, int cnt) {

        if (cnt == n * m) {
            if (min_result == -1 || min_result > result) {
                min_result = result;
                return;
            }
        }

        int x = i + dx[direct];
        int y = j + dy[direct];

        if (x >= 0 && x < n && y >= 0 && y < m && !visited[x][y]) {
            visited[x][y] = true;
            search(x, y, result, direct, cnt + 1);
            visited[x][y] = false;
        } else {
            for (int k = 0; k < 4; k++) {
                if (k == direct)
                    continue;
                int a = i + dx[k];
                int b = j + dy[k];
                if (a >= 0 && a < n && b >= 0 && b < m && !visited[a][b]) {
                    visited[a][b] = true;
                    search(a, b, result + 1, k, cnt + 1);
                    visited[a][b] = false;
                }
            }
        }
    }
}