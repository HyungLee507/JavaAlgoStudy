package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_3079 {
	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 입국심사대
		arr = new int[N];

		long max = 0;
		// 입국심사대에 걸리는 시간
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, arr[i]);
		}

		long left = 0;
		long right = max * M;
		long mid = 0;

		while (left + 1 < right) {
			mid = (left + right) / 2;
			long pCnt = 0;
			for (int i = 0; i < N; i++) {
				pCnt += mid / arr[i];
				if (pCnt >= M) {
					break;
				}
			}
			if (pCnt < M) {
				left = mid;
			} else {
				right = mid;
			}
		}

		System.out.println(right);
		br.close();
	}
}
