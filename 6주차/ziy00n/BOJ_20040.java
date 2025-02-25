package ziy00n;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20040 {
	static int n, m;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		parent = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());


			if (!union(x, y)) {
				sb.append(i);
				System.out.println(sb.toString());
				return;
			}
		}

		sb.append(0);
		System.out.println(sb.toString());

		br.close();
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);

		// 같은 부모를 가지고 있다면 사이클이 완성된 것
		if (x == y) {
			return false;
		}
		// 더 작은 노드를 부모로 설정
		if (x <= y) {
			parent[y] = x;
		} else {
			parent[x] = y;
		}
		return true;
	}

	static int find(int x) {
		if (x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
}
