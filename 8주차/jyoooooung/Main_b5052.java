package week8;

import java.io.*;
import java.util.*;

public class Main_b5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=t; tc++) {
            int n = Integer.parseInt(br.readLine());
            Trie trie = new Trie();
            boolean ans = true;
            String s;
            for (int i = 0; i < n; i++) {
                s = br.readLine();
                if (!trie.insert(s)) ans = false;
            }
            if (!ans) System.out.println("NO");
            else System.out.println("YES");
        }
    }

    static class TrieNode {
        private Map<Character, TrieNode> childNode = new HashMap<>();
        private boolean isLast;

        public Map<Character, TrieNode> getChildNode() {
            return childNode;
        }

        public boolean isLast() {
            return isLast;
        }

        public void setLast(boolean last) {
            isLast = last;
        }
    }

    static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public boolean insert(String word) {
            TrieNode node = this.root;
            boolean isPrefix = false;
            for (int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                if (!node.childNode.containsKey(c)) {
                    node.childNode.put(c, new TrieNode());
                }
                node = node.childNode.get(c);
                if (node.isLast) {
                    return false;
                }
            }
            if (!node.getChildNode().isEmpty()) {
                return false;
            }

            node.setLast(true);
            return true;
        }
    }
}
