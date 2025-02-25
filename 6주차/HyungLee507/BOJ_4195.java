package algostudy.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class BOJ_4195 {

    private static int[] parent;
    private static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            int networkCnt = Integer.parseInt(bf.readLine());
            parent = IntStream.range(0, networkCnt * 2).toArray();
            count = new int[networkCnt * 2];
            Arrays.fill(count, 1);
            HashMap<String, Integer> map = new HashMap<>();
            int index = 0;
            for (int k = 0; k < networkCnt; k++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                String friend1 = st.nextToken();
                String friend2 = st.nextToken();
                if (!map.containsKey(friend1)) {
                    map.put(friend1, index++);
                }
                if (!map.containsKey(friend2)) {
                    map.put(friend2, index++);
                }
                sb.append(union(map.get(friend1), map.get(friend2))).append('\n');
            }
        }
        System.out.println(sb);
        bf.close();
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    private static int union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            if (a < b) {
                parent[b] = a;
                count[a] += count[b];
                return count[a];
            } else {
                parent[a] = b;
                count[b] += count[a];
                return count[b];
            }
        }
        return count[a];
    }

}
