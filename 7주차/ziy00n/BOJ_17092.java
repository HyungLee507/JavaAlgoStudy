package ziy00n;

import java.io.*;
import java.util.*;

public class BOJ_17092 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		long H = Long.parseLong(st.nextToken());
		long W = Long.parseLong(st.nextToken());
		long N = Long.parseLong(st.nextToken());

		Set<Long> blackCells = new HashSet<>();

		for (long i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			long r = Long.parseLong(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			blackCells.add(hash(r, c));  // 좌표를 해싱하여 저장
		}

		Map<Long, Integer> countMap = new HashMap<>();

		for (long cell : blackCells) {
			long r = cell >> 32;  // 해싱된 좌표 복원
			long c = cell & 0xFFFFFFFFL;

			// (r, c)를 포함하는 모든 3×3 격자의 오른쪽 아래 좌표 (i, j) 탐색
			for (long dr = 0; dr <= 2; dr++) {
				for (long dc = 0; dc <= 2; dc++) {
					long i = r + dr, j = c + dc;
					if (i >= 3 && i <= H && j >= 3 && j <= W) {
						long key = hash(i, j);  // 오른쪽 아래 좌표를 해싱
						countMap.put(key, countMap.getOrDefault(key, 0) + 1);
					}
				}
			}
		}

		// 결과 배열 (0~9개의 검은색 개수를 가지는 3×3 격자의 개수)
		long[] result = new long[10];

		// HashMap에 저장된 검은색 개수별 개수 계산
		for (int value : countMap.values()) {
			result[value]++;
		}

		long totalGrids = (H - 2) * (W - 2);
		result[0] = totalGrids - countMap.size();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= 9; i++) {
			sb.append(result[i]).append("\n");
		}
		System.out.print(sb.toString());
	}

	// 좌표를 Long 타입으로 변환하는 해싱 함수
	private static long hash(long r, long c) {
		return (r << 32) | (c & 0xFFFFFFFFL);
	}
}
