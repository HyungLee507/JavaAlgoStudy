package ziy00n;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_4195 {
	static int[] parent;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			int F = Integer.parseInt(br.readLine());

			parent = new int[F * 2];
			arr = new int[F * 2];
			for (int i = 0; i < F * 2; i++) {
				parent[i] = i;
				arr[i] = 1;
			}

			int idx = 0;
			Map<String, Integer> map = new HashMap<>();

			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();

				if (!map.containsKey(a)) {
					map.put(a, idx++);
				}

				if (!map.containsKey(b)) {
					map.put(b, idx++);
				}

				sb.append(union(map.get(a), map.get(b)) + "\n");
			}
		}

		System.out.println(sb.toString());
		br.close();
	}

	public static int find(int x) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	public static int union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			if (x < y) {
				parent[y] = x;
				arr[x] += arr[y];
				return arr[x];
			} else {
				parent[x] = y;
				arr[y] += arr[x];
				return arr[y];
			}
		}
		return arr[x];
	}
}
