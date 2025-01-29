package javaAlgorithm;

import java.io.*;
import java.util.*;

public class Main_b1405 {

	static int n;
	static int E, W, S, N;
	static double[] direction;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static double answer = 0;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		direction = new double[4];
		n = Integer.parseInt(st.nextToken());
		for (int i=0; i<4; i++) {
			direction[i] = Double.parseDouble(st.nextToken()) * 0.01;
		}
		// direction  : [E][W][S][N]
		
		visited = new boolean[2*n+1][2*n+1];
		move(n, n, 0, 1);
		System.out.println(answer);
	}
	
	static void move(int x, int y, int cnt, double probability) {
		if (cnt == n) {
			answer += probability;
			return;
		}
		visited[x][y] = true;
		for (int t=0; t<4; t++) {
			int xi = x + dx[t];
			int yi = y + dy[t];
			if (0<=xi && xi<2*n+1 && 0<=yi && yi<2*n+1 && !visited[xi][yi]) {
				visited[xi][yi] = true;
				move(xi, yi, cnt+1, probability*direction[t]);
				visited[xi][yi] = false;
			}
		}
	}

}
