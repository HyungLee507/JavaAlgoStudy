package algostudy.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class BOJ_20040 {
    static int n, m, result;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = IntStream.range(0, n).toArray();
        result = 0;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (find(start) == find(end)) {
                result = result == 0 ? i : result;
            }
            union(start, end);
        }
        System.out.println(result);
    }

    private static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py) {
            parent[py] = px;
        }
    }

    private static int find(int k) {
        if (parent[k] == k) {
            return k;
        } else {
            return parent[k] = find(parent[k]);
        }
    }
}

