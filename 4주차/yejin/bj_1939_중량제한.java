import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<List<Bridge>> graph = new ArrayList<>();
    static int start, end;

    static class Bridge {
        int to, weight;
        Bridge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 다리 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new Bridge(B, C));
            graph.get(B).add(new Bridge(A, C));
        }

        // 출발점과 도착점 입력
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // 이분 탐색 실행
        int left = 1, right = 1_000_000_000, answer = left;

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (canCross(mid)) { // mid 무게로 이동 가능한지 확인
                left = mid;  // 가능하면 더 큰 중량을 탐색
            } else {
                right = mid; // 불가능하면 더 작은 중량을 탐색
            }
        }

        System.out.println(left);
    }

    // BFS를 이용해 start -> end로 mid 중량 이상으로 이동 가능한지 확인
    static boolean canCross(int mid) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == end) return true; // 도착하면 성공

            for (Bridge bridge : graph.get(current)) {
                if (!visited[bridge.to] && bridge.weight >= mid) { // 중량 제한 충족
                    visited[bridge.to] = true;
                    queue.add(bridge.to);
                }
            }
        }
        return false;
    }
}
