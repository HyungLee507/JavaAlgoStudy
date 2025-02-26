package bj_2665_미로만들기;

import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { 1, 0, -1, 0 };

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int[][] dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        dist[0][0] = 0;
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));

        while(!pq.isEmpty()){
            Node node = pq.poll();

            if(node.dist > dist[node.x][node.y]){
                continue;
            }

            for(int d = 0; d < 4; d++){
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];
                if(0 <= nx && nx < N && 0 <= ny && ny < N){
                    int cost = node.dist + (map[nx][ny] == 0 ? 1 : 0); // 검은 방이면 비용 +1

                    if(cost < dist[nx][ny]){
                        dist[nx][ny] = cost;
                        pq.offer(new Node(nx, ny, dist[nx][ny]));
                    }
                }
            }
        }
        System.out.println(dist[N-1][N-1]);

    }
}
