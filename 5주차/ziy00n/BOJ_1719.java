import java.io.*;
import java.util.*;

public class BOJ_1719 {

	static int n, m;
	static List<Node>[] graph;
	static int[][] arr;

	static StringBuilder sb = new StringBuilder();

	static class Node {
		int end;
		int cost;

		public Node(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][n];
		graph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[start].add(new Node(end, cost));
			graph[end].add(new Node(start, cost));
		}

		// 1번 부터 n번까지 최단 경로 찾기
		for (int i = 1; i <= n; i++) {
			dijkstra(i);
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					sb.append("-").append(" ");
				} else {
					sb.append(arr[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		pq.offer(new Node(start, 0));

		int[] dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		boolean[] visited = new boolean[n + 1];

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (dist[cur.end] < cur.cost) continue;

			if (visited[cur.end]) continue;
			visited[cur.end] = true;

			for (Node node : graph[cur.end]) {
				if (visited[node.end]) continue;

				if (dist[node.end] > cur.cost + node.cost) {
					dist[node.end] = cur.cost + node.cost; // 최단거리 갱신
					pq.add(new Node(node.end, dist[node.end]));
					arr[node.end - 1][start - 1] = cur.end; //  경로표
				}
			}
		}
	}
}