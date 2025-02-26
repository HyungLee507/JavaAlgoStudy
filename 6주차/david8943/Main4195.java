import java.io.*;
import java.util.*;

public class Main4195 {
    static Map<String, Integer> nameToIndex = new HashMap<>();
    static int[] parent;
    static int[] friendCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int F = Integer.parseInt(br.readLine());

            nameToIndex.clear();
            parent = new int[2 * F]; // 최대 2*F명의 사람이 있을 수 있음
            friendCount = new int[2 * F];

            for (int i = 0; i < 2 * F; i++) {
                parent[i] = i;      // 각자 자신을 부모로 초기화
                friendCount[i] = 1; // 처음에는 자기 자신만 존재하므로 1로 초기화
            }

            int personCount = 0;

            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String person1 = st.nextToken();
                String person2 = st.nextToken();

                // 처음 등장하는 사람이면 인덱스를 부여
                if (!nameToIndex.containsKey(person1)) {
                    nameToIndex.put(person1, personCount++);
                }
                if (!nameToIndex.containsKey(person2)) {
                    nameToIndex.put(person2, personCount++);
                }

                int idx1 = nameToIndex.get(person1);
                int idx2 = nameToIndex.get(person2);

                // 두 사람을 합치고, 친구 네트워크의 크기를 출력
                sb.append(union(idx1, idx2)).append("\n");
            }
        }

        System.out.print(sb);
    }

    // Find 연산
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]); // 경로 압축
    }

    // Union 연산
    static int union(int x, int y) {
        x = find(x);
        y = find(y);

        // 이미 같은 친구 네트워크에 속해 있는 경우
        if (x == y) {
            return friendCount[x];
        }

        // 두 네트워크를 합치고 크기를 갱신
        parent[y] = x;
        friendCount[x] += friendCount[y];
        return friendCount[x];
    }
}