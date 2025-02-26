package bj_20040_사이클게임;

import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;

    static int find(int x){
        while(x != parent[x]){
            x = parent[x];
        }
        return x;
    }

    static boolean union(int x, int y){ // 번호가 작은 쪽이 부모가 되도록
        x = find(x);
        y = find(y);
        if(x == y) return false;
        if(x <= y) parent[y] = x;
        else parent[x] = y;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N];

        for(int i = 0; i < N; i++) {
            parent[i] = i;
        }

        int answer = 0;
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(!union(x, y)) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }
}
