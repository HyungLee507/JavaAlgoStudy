package javaAlgorithm;

import java.io.*;
import java.util.*;

public class Main_b16988 {

	static int N, M;
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int answer = 0;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		baDuk2(0);
		System.out.println(answer);

	}

	static void baDuk2(int cnt) {
		if (cnt == 2) {
			killingCount(map);
			return;
		}
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					baDuk2(cnt+1);
					map[i][j] = 0;
				}
			}
		}
	}

	static void killingCount(int[][] map) {
		visited = new boolean[N][M];
		int cnt = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] == 2 && !visited[i][j]) {
					Queue<int[]> queue = new LinkedList<>();
					boolean kill = true;
					queue.offer(new int[] {i,j});
					visited[i][j] = true;
					int count = 1;
					while (!queue.isEmpty()) {
						int[] temp = queue.poll();
						for (int t=0; t<4; t++) {
							int xi = temp[0] + dx[t];
							int yi = temp[1] + dy[t];
							if (0<=xi && xi<N && 0<=yi && yi<M && !visited[xi][yi]) {
								if (map[xi][yi] == 0) kill = false;
								if (map[xi][yi] == 2) {
									queue.offer(new int[] {xi, yi});
									visited[xi][yi] = true;
									count++;
								}
							}
						}
					}
					if (kill) cnt += count;
				}
			}
		}
		answer = Math.max(cnt, answer);
	}

}
