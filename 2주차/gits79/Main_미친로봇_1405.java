package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main_미친로봇_1405 {

    // 최대 14칸을 움직일 수 있음
    // 0, 0 에서 시작하기 위해
    // 30, 30 사이즈로 설정
    static int N, size = 30;
    static double answer = 0;
    static int[] arr;
    static boolean[][] v = new boolean[size][size];
    // 문제는 상하좌우가 아닌 우, 좌 ,하 ,상
    // 순서 상관없다 생각했으나 좌우, 상하가 다름
    static final int[] di = {0, 0, 1, -1};
    static final int[] dj = {1, -1, 0, 0};

    static void recur(int cur, int i, int j, double sum) {

        // N번 이동을 완료한 경우
        if (cur == N) {
            answer += sum;
            return;
        }

        v[i][j] = true;
        // 4방 탐색 진행
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if (checkIdx(ni, nj) && !v[ni][nj]) {
                // 방문 처리
                v[ni][nj] = true;
                // 이동횟수 증가와 갈 수 있는 확률 더해줌
                recur(cur + 1, ni, nj, sum * arr[d] * 0.01);
                // 방문 처리 풀어서 다시 방문할 수 있도록
                // PermMain과 동일 원리
                v[ni][nj] = false;
            }

        }

    }

    static boolean checkIdx(int i, int j) {
        if (0 <= i && i < size && 0 <= j && j < size) return true;
        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        recur(0, 15, 15, 1);
        System.out.println(answer);

    }
}
