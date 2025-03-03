import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_2665 {

	static int n;
	static int[][] board, dist;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	static class Node {
		int r;
		int c;
		int w;

		public Node(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		dist = new int[n][n];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				board[i][j] = str.charAt(j) - '0';
				dist[i][j] = Integer.MAX_VALUE; // 무한대로 초기화
			}
		}

		dijkstra();

		System.out.println(dist[n - 1][n - 1]);
		br.close();
	}

	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return Integer.compare(n1.w, n2.w);
			}
		});

		pq.offer(new Node(0, 0, 0));
		dist[0][0] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (dist[cur.r][cur.c] < cur.w)
				continue; // 이미 더 최소이면 안바꿈

			for (int d = 0; d < 4; d++) {
				int rr = cur.r + dy[d];
				int cc = cur.c + dx[d];
				if (0 > rr || rr >= n || 0 > cc || cc >= n)
					continue;

				// 검은 방
				if (board[rr][cc] == 0) {
					int nw = cur.w + 1;
					if (nw >= dist[rr][cc]) continue;
					dist[rr][cc] = nw;
					pq.add(new Node(rr, cc, nw));
				} // 흰 방
				else {
					int nw = cur.w;
					if (nw >= dist[rr][cc]) continue;
					dist[rr][cc] = nw;
					pq.add(new Node(rr, cc, nw));
				}

			}
		}
	}
}
