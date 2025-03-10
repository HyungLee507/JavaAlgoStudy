package ziy00n;

import java.io.*;
import java.util.*;

public class BOJ_5052 {
	static int n;

	static class TrieNode {
		Map<Character, TrieNode> childNodes = new HashMap<>();
		boolean isLast = false;
	}

	static class Trie {
		private TrieNode rootNode;

		Trie() {
			rootNode = new TrieNode();
		}

		boolean insert(String word) {
			TrieNode node = this.rootNode;
			for (char ch : word.toCharArray()) {
				if (node.isLast) return false; // 1. 기존 번호가 새로운 번호의 접두사면 false
				node = node.childNodes.computeIfAbsent(ch, c -> new TrieNode());
			}
			if (!node.childNodes.isEmpty()) return false; // 2. 현재 번호가 기존 번호의 접두사면 false
			node.isLast = true;
			return true;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			Trie trie = new Trie();
			boolean flag = true;
			n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				String word = br.readLine();
				if (!flag) continue;
				if (!trie.insert(word)) {
					flag = false;
				}
			}
			sb.append(flag ? "YES" : "NO").append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
