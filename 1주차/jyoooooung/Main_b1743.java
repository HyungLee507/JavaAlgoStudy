package javaAlgorithm;

import java.io.*;
import java.util.*;

public class Main_b1743 {
	static int N, M, K;
	static int[][] maps;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		maps = new int[N][M];
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			maps[x-1][y-1] = 1;
		}

		bfs();
		System.out.println(answer);
	}

	static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (maps[i][j] == 1) {
					queue.offer(new int[] {i, j});
					visited[i][j] = true;
					int cnt = 1;
					while (!queue.isEmpty()) {
						int[] temp = queue.poll();
						for (int t=0; t<4; t++) {
							int xi = temp[0] + dx[t];
							int yi = temp[1] + dy[t];
							if (0<=xi && xi<N && 0<=yi && yi<M && maps[xi][yi]==1 && !visited[xi][yi]) {
								queue.offer(new int[] {xi, yi});
								visited[xi][yi] = true;
								cnt++;
							}
						}
					}
					answer = Math.max(answer, cnt);
				}
			}
		}
	}
}
