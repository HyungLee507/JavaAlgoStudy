package javaAlgorithm;

import java.io.*;
import java.util.*;

class Node {
    int to;
    int cost;

    public Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}
public class Main_b1240 {
    static int N, M;
    static List<Node>[] graph;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[f].add(new Node(t,c));
            graph[t].add(new Node(f, c));

        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            answer = Integer.MIN_VALUE;
            dfs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
            System.out.println(answer);
        }
    }

    static void dfs(int f, int t, int cnt) {
        if (f == t) {
            answer = Math.max(answer, cnt);
            return;
        }
        for (Node n : graph[f]) {
            dfs(n.to, t, cnt+n.cost);
        }
    }
}
