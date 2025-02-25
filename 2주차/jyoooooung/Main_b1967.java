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

public class Main_b1967 {

	static int n;
	static List<Node>[] tree;
	static int answer = 0;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		
		tree = new ArrayList[n+1];
		for (int i=0; i<=n; i++) {
			tree[i] = new ArrayList<>();
		}
		
		visited = new boolean[n+1];
		
		for (int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine()); 
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			tree[f].add(new Node(t,c));
			tree[t].add(new Node(f,c));
		}

		for (int i=1; i<=n; i++) {
			if (tree[i].size() == 1) { // 트리의 자식이 없음 : 말단
				visited[i] = true;
				delimeter(i, 0);
				visited[i] = false;
			}
		}
		System.out.println(answer);
	}
	
	static void delimeter(int f, int ans) {
		if (tree[f].size() == 1 && ans != 0) {
			answer = Math.max(ans, answer);
			return;
		}
		for (Node n : tree[f]) {
			if (!visited[n.to]) {
				visited[n.to] = true;
				delimeter(n.to, ans+n.cost);
				visited[n.to] = false;
			}
		}
	}
}
