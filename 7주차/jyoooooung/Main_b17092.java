package week7;

import java.io.*;
import java.util.*;

public class Main_b17092 {
    static int H, W, N;
    static long[][] countBox;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        countBox = new long[H-2][W-2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            findGrid(x, y);
        }

        for (long[] a:countBox) System.out.println(Arrays.toString(a));

        long[] answer = new long[10];
        for (long[] cb : countBox) {
            for (long c : cb) {
                answer[(int) c]++;
            }
        }
        System.out.println(Arrays.toString(answer));
    }

    static void findGrid(int x, int y) {
        for (int i=-2; i<=0; i++) {
            for (int j=-2; j<=0; j++) {
                int xi = x + i;
                int yj = y + j;
                if (0<=xi && xi<H-2 && 0<=yj && yj<W-2) {
                    countBox[xi][yj]++;
                }
            }
        }
    }
}
