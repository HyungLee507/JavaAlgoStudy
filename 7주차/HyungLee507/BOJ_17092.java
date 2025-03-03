import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static final long CONST = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        HashMap<Long, Integer> map = new HashMap<>();
        st = new StringTokenizer(br.readLine(), " ");
        long h = Long.parseLong(st.nextToken());
        long w = Long.parseLong(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            long r = Long.parseLong(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken()) - 1;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int val = i * 3 + j;
                    if (r - 2 + i < 0 || c - 2 + j < 0 || r + i >= h || c + j >= w) {
                        continue;
                    }
                    long hash = (r - 2 + i) * CONST + (c - 2 + j);
                    map.put(hash, map.getOrDefault(hash, 0) | (1 << val));
                }
            }
        }
        long[] res = new long[10];
        res[0] = (h - 2) * (w - 2);
        for (long key : map.keySet()) {
            int cnt = Long.bitCount(map.get(key));
            res[0] -= 1;
            res[cnt] += 1;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(res[i]);
        }
        br.close();
    }
}
