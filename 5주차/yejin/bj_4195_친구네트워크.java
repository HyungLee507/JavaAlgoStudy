package bj_4195_친구네트워크;

import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;
    static int[] size;

    static int find(int x) {
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }

    static int union(int x, int y) { // 리턴 타입은 네트워크 사이즈
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (size[rootX] > size[rootY]) {
                size[rootX] += size[rootY];
                parent[rootY] = rootX;
                return size[rootX];
            } else {
                size[rootY] += size[rootX];
                parent[rootX] = rootY;
                return size[rootY];
            }
        }
        return size[rootX];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            int F = Integer.parseInt(br.readLine());
            parent = new int[F * 2];
            size = new int[F * 2];
            for(int i = 0; i < F * 2; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            HashMap<String, Integer> map = new HashMap<>();
            int index = 0;
            for(int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();

                if(!map.containsKey(f1)) {
                    map.put(f1, index++);
                }
                if(!map.containsKey(f2)) {
                    map.put(f2, index++);
                }
                sb.append(union(map.get(f1), map.get(f2))).append("\n");
            }
        }
        System.out.println(sb);
    }
}
