import java.io.*;
import java.util.*;

public class Main_b10844 {
	
	static long mod = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[][] answer = new long[N][10];
		Arrays.fill(answer[0], 1);
		answer[0][0] = 0;		
		
		for (int i=1; i<N; i++) {
			answer[i][0] = answer[i-1][1] % mod;
			for (int j=1; j<9; j++) {
				answer[i][j] = (answer[i-1][j-1] + answer[i-1][j+1]) % mod;
			}
			answer[i][9] = answer[i-1][8] % mod;
		}
		
		long sum = 0;
		for (int i=0; i<10; i++) {
			sum = (sum + answer[N-1][i]) % mod;
		}
		System.out.println(sum);
		
	}
}
