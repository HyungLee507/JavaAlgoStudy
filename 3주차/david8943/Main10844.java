import java.io.*;
import java.util.*;

public class Main10844 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[][] dp = new long[10][n+1];

        //dp[i][j] : i로 시작하면서 길이가 j인 계단 수

        for(int i=0;i<=9;i++){
            dp[i][1] = 1;
        }
        for(int i=2;i<=n;i++){
            dp[0][i] = dp[1][i-1];
            for(int j=1;j<=8;j++){
                dp[j][i] = (dp[j-1][i-1] + dp[j+1][i-1])%1000000000;
            }
            dp[9][i] = dp[8][i-1];
        }
        long result = 0;
        for(int i=1;i<=9;i++){
            result = (dp[i][n]+result)%1000000000;
        }
        System.out.println(result);
    }
}
