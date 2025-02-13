package backjoon.study.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3079 {
    static int[] immigrationTime;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        immigrationTime = new int[N];
        for (int i = 0; i < N; i++) {
            immigrationTime[i] = Integer.parseInt(bf.readLine());
        }
        Arrays.sort(immigrationTime);
        long left = 0;
        long right = ((long) immigrationTime[N - 1]) * M + 1;
        long mid = 0;
        while (left + 1 < right) {
            mid = (left + right) / 2;
            if (coverAll(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        System.out.println(right);
        bf.close();
    }

    private static boolean coverAll(long limit) {
        long temp = 0;
        for (int i = 0; i < N; i++) {
            temp += (limit / immigrationTime[i]);
            if (temp >= M) {
                return true;
            }
        }
        return false;
    }
}
