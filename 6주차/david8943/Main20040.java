import java.util.*;
import java.io.*;

public class Main20040 {
    static int[] parent;

    static int find(int x) {
        if (x != parent[x]) {
            return parent[x] = find(parent[x]);
        } else {
            return x;
        }
    }

    static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) return false;

        if(parent[rootX] < parent[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int result = 0;

        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            if(!union(n1, n2)) { // 부모가 같으면
                result = i + 1;
                break;
            }
        }
        System.out.println(result);
    }
}
