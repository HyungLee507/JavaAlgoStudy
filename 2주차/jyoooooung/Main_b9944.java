package javaAlgorithm;

import java.io.*;
import java.util.*;

public class Main_b9944 {

	static int N, M;
	static int[][] board;
	static int blank;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int answer;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int tc = 1;
		StringBuilder sb = new StringBuilder();
		String input = "";
		
		while ((input = br.readLine()) != null && !input.isEmpty()) {
			st = new StringTokenizer(input);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
		
			board = new int[N][M];
			visited = new boolean[N][M];
			blank = 0;
			answer = Integer.MAX_VALUE;
		
			for (int i=0; i<N; i++) {
				String s = br.readLine();
				for (int j=0; j<M; j++) {
					if (s.charAt(j) == '*') board[i][j] = 1;
					else blank++;
				}
			}
		
		// 빈칸의 개수
			if (blank == 1) answer = 0;
			else game();
			sb.append("Case ").append(tc).append(": ")
				.append(answer == Integer.MAX_VALUE ? -1:answer)
				.append("\n");
			tc++;
		}
		System.out.println(sb.toString());
		
	}
	
	// 공의 위치를 정해준다.
	static void game() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (board[i][j] == 0) {
					for (int t=0; t<4; t++) {
						visited[i][j] = true;
						backTracking(i, j, t, 1, 1);
						visited[i][j] = false;
					}
				}
			}
		}
	}
	
	// 모든 칸을 방문할 수 있는지 확인, 방문할 수 있다면 cnt ans에 반영
	// 한번 방향을 정하면 쭉 가기, 
	static void backTracking(int x, int y, int dir, int cnt, int ans) {
		if (cnt == blank) {
			answer = Math.min(ans, answer);
			return;
		}
		
		int xi = x + dx[dir];
		int yi = y + dy[dir];
		if (0<=xi && xi<N && 0<=yi && yi<M && board[xi][yi] == 0 && !visited[xi][yi]) {
			visited[xi][yi] = true;
			backTracking(xi, yi, dir, cnt+1, ans);
			visited[xi][yi] = false;
		}
		
		else {
			for (int t=0; t<4; t++) {
				xi = x + dx[t];
				yi = y + dy[t];
				if (0<=xi && xi<N && 0<=yi && yi<M && board[xi][yi] == 0 && !visited[xi][yi]) {
					visited[xi][yi] = true;
					backTracking(xi, yi, t, cnt+1, ans+1);
					visited[xi][yi] = false;
				}
	
			}
		
		}
	}
}
