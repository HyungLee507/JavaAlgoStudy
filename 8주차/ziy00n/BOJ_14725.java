package ziy00n;

import java.io.*;
import java.util.*;

public class BOJ_14725 {
	static StringBuilder sb;

	static class TrieNode {
		// 자식 노드맵
		Map<String, TrieNode> childNodes = new HashMap<>();
	}

	static class Trie {
		// 루트 노드
		private TrieNode rootNode;

		// 생성자로 root 노드 초기화
		Trie() {
			rootNode = new TrieNode();
		}

		void insert(String[] words) {
			TrieNode node = this.rootNode;

			for (String word : words) {
				node = node.childNodes.computeIfAbsent(word, str -> new TrieNode());
			}
		}

		void print(TrieNode node, int depth) {
			List<String> keys = new ArrayList<>(node.childNodes.keySet());// 자식노드들의 key들을 리스트에 저장
			Collections.sort(keys);

			for (String key : keys) {
				for (int i = 0; i < depth; i++) {
					sb.append("--");
				}
				sb.append(key).append("\n");
				print(node.childNodes.get(key), depth + 1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		Trie trie = new Trie();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int K = Integer.parseInt(st.nextToken());
			String[] words = new String[K];
			for (int j = 0; j < K; j++) {
				words[j] = st.nextToken();
			}
			trie.insert(words);
		}

		trie.print(trie.rootNode, 0);
		System.out.println(sb.toString());

		br.close();
	}
}
