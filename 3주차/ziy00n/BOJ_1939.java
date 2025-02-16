package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1939 {
	static int N, M;
	static List<int[]>[] graph;
	static int start, end;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		int max = 0; // 최대 중량 저장 변수
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph[a].add(new int[]{b, w});
			graph[b].add(new int[]{a, w});

			max = Math.max(max, w);
		}

		st = new StringTokenizer(br.readLine(), " ");
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		int left = 1;
		int right = max + 1;
		while (left + 1 < right) {
			int mid = (left + right) / 2;

			if (bfs(mid)) { // mid로 출발섬 -> 도착섬 이동 가능하면
				left = mid; // mid 중량 늘리기
			} else { // 불가능하면
				right = mid; // 중량 줄임
			}
		}

		System.out.println(left);
		br.close();
	}

	// start -> end 가는 다리를 거칠때, mid 중량을 다리가 버틸 수 있는지 확인
	static boolean bfs(int mid) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		q.offer(start);
		visited[start] = true;

		while (!q.isEmpty()) {
			int node = q.poll();

			if (node == end) { // 도착섬 도달
				return true;
			}

			for (int[] next : graph[node]) {
				int nn = next[0]; // 다음 섬
				int nw = next[1]; // 해당 다리의 중량 제한

				if (visited[nn]) continue;
				if (nw < mid) continue; // 다리 중량이 mid보다 작어서 버틸 수 X면 넘김

				visited[nn] = true;
				q.offer(nn);
			}
		}
		return false; // end 섬 못가면 false
	}
}
