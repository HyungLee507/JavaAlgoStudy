import java.util.*;
import java.io.*;

public class Main11066 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n+1];
            int[] sum = new int[n+1];
            int[][] dp = new int[n+1][n+1];

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i-1] + arr[i];
            }

            for(int length=1;length<n;length++){
                for(int start=1;start+length<=n;start++){
                    int end = start+length;
                    dp[start][end] = Integer.MAX_VALUE;

                    for(int mid=start;mid<end;mid++){
                        dp[start][end]=Math.min(dp[start][end],dp[start][mid]+dp[mid+1][end]+sum[end]-sum[start-1]);
                    }
                }
            }
            sb.append(dp[1][n]).append("\n");
        }
        System.out.println(sb);
    }
}
