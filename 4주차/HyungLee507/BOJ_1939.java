package backjoon.study.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1939 {
    static int N, M, START, END;
    static boolean[] visited;

    static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static ArrayList<Node>[] adjustNodes;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        //init
        adjustNodes = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adjustNodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjustNodes[start].add(new Node(end, weight));
            adjustNodes[end].add(new Node(start, weight));
        }
        st = new StringTokenizer(bf.readLine());
        START = Integer.parseInt(st.nextToken());
        END = Integer.parseInt(st.nextToken());

        long left = 0;
        long right = 1_000_000_001;
        while (left + 1 < right) {
            long mid = (left + right) / 2;
            if (canReach(mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.println(left);
        bf.close();
    }

    private static boolean canReach(long mid) {
        visited = new boolean[N + 1];
        visited[START] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(START);
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            for (Node next : adjustNodes[poll]) {
                if (next.weight >= mid && !visited[next.to]) {
                    if (next.to == END) {
                        return true;
                    }
                    visited[next.to] = true;
                    queue.add(next.to);
                }
            }
        }
        return false;
    }
}
