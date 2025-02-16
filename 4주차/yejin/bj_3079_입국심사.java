package bj_3079_입국심사;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());
        long[] desk = new long[N];

        long max = 0;
        for(int i = 0; i < N; i++){
            desk[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, desk[i]);
        }

        long left = 1;
        long right = max * M;

        while(left + 1 < right) {
            long mid = (left + right) / 2;
            long cnt = 0;
            for(int i = 0; i < N; i++){
                cnt += mid / desk[i];
                if(cnt >= M){
                    break;
                }
            }
            if(cnt >= M){ // mid분에는 넉넉하게 심사 종료, mid를 더 줄이자
                right = mid;
            } else {
                left = mid;
            }
        }
        System.out.println(right);
    }
}