package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main_입국심사_3079 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }


        long left = 0;
        // 단순히 제일 큰 long 값이 아닌 M명이 하나에 몰릴 경우 계산
        long right = (long) max * M;
        long cnt;

        while (left + 1 < right) {
            // mid는 최소 시간의 값
            long mid = (left + right) / 2;
            // cnt는 입국 심사 통과한 사람 수
            cnt = 0;

            // 각 입국 심사대에서 최소 시간(mid) 동안 몇명을 처리할 수 있는지 계산
            for(int i = 0; i < N; i++) {
                cnt += mid / arr[i];
                // 여기서 자꾸 long 범위 터지는 듯
                if(cnt >= M) break;
            }
            // M명 이상 처리가 가능하다면 최소 시간(mid)를 줄여서 더 가능한지 계산
            if(cnt >= M) right = mid;
            // 아니라면 최소 시간(mid) 더 필요
            else left = mid;


        }
        System.out.println(right);

    }
}
