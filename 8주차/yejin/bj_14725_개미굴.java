import java.util.*;
import java.io.*;

public class Main {
    static class TrieNode {
        TreeMap<String, TrieNode> children;

        TrieNode() {
            children = new TreeMap<>();
        }
    }

    static class Trie {
        TrieNode root;
        Trie() {
            root = new TrieNode();
        }
        public void insert(StringTokenizer st) {
            TrieNode node = root;
            while(st.hasMoreTokens()) {
                String token = st.nextToken();
                if(!node.children.containsKey(token)) {
                    node.children.put(token, new TrieNode());
                }
                node = node.children.get(token);
            }
        }
        public void print(TrieNode node, int depth) {
            for (String key : node.children.keySet()) {
                StringBuilder prefix = new StringBuilder();
                for (int i = 0; i < depth; i++) {
                    prefix.append("--");
                }
                System.out.println(prefix.toString() + key);
                print(node.children.get(key), depth + 1); // 재귀 호출
            }
        }
}

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Trie trie = new Trie();
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            trie.insert(st);
        }
        trie.print(trie.root, 0);
    }
}
