import java.io.;
import java.util.;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] blackCells = new int[N][2];
        for (int i = 0; i  N; i++) {
            st = new StringTokenizer(br.readLine());
            blackCells[i][0] = Integer.parseInt(st.nextToken());
            blackCells[i][1] = Integer.parseInt(st.nextToken());
        }

         3x3 영역에 포함된 검은 칸 개수 저장
        MapString, Integer countMap = new HashMap();

         모든 검은 칸을 돌면서, 포함되는 3x3 부분 갱신
        for (int i = 0; i  N; i++) {
            int r = blackCells[i][0];
            int c = blackCells[i][1];

            for (int dr = 0; dr  3; dr++) {
                for (int dc = 0; dc  3; dc++) {
                    int nr = r - dr;
                    int nc = c - dc;

                    if (nr = 1 && nr = H - 2 && nc = 1 && nc = W - 2) {
                        String key = nr + , + nc;
                        countMap.put(key, countMap.getOrDefault(key, 0) + 1);
                    }
                }
            }
        }

        long[] result = new long[10];
        for (int value  countMap.values()) {
            result[value]++;
        }

        long total3x3 = (long) (H - 2)  (W - 2);
        long sum = 0;
        for (int i = 1; i = 9; i++) {
            sum += result[i];
        }
        result[0] = total3x3 - sum;

        for (int i = 0; i  10; i++) {
            System.out.println(result[i]);
        }
    }
}
