import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static int N, M;
    static int[] parents;

    static void make() {
        for (int i = 0; i < N; i++) {
            parents[i] = i;  // make-set i : 자신의 부모를 자신으로하여 대표자가 되도록 설정
        }
    }

    static int findSet(int a) {
        if (a == parents[a]) return a; // 자신이 자신의 부모라면 루트노드이고 집합의 대표자가 됨
        return parents[a] = findSet(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false; // 두 집합의 대표자가 같으면 이미 같은 집합, 합집합 불가
        // aRoot에 bRoot를 흡수 : 두 집합 합치기
        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N];
        make();
        
        int answer = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!union(a, b)) {
                answer = i + 1;
                break;
            }
        }

        System.out.println(answer);

    }
}
