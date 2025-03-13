package week8;

import java.io.*;
import java.util.*;

public class Main_b14725 {
    static int N;

    public static void main(String[] arg) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        Trie trie = new Trie();
        for (int i=0; i<N; i++) {
            String[] input = br.readLine().split(" ");
            int k = Integer.parseInt(input[0]);
            trie.insert(Arrays.copyOfRange(input, 1, input.length));
        }

        trie.printAntRoad(trie.getRootNode(), 0);
    }

    static class TrieNode {
        private Map<String, TrieNode> childNode = new HashMap<>();
        private boolean isLast;

        public Map<String, TrieNode> getChildNodes() {
            return childNode;
        }

        public boolean isLast() {
            return isLast;
        }

        public void setLast(boolean last) {
            this.isLast = last;
        }
    }

    static class Trie {
        private TrieNode rootNode;

        public Trie() {
            rootNode = new TrieNode();
        }

        public TrieNode getRootNode() {
            return rootNode;
        }

        public void insert(String[] feeds) {
            TrieNode node = this.rootNode;
            for (String f : feeds) {
                node = node.getChildNodes().computeIfAbsent(f, c-> new TrieNode());
            }
            node.setLast(true);
        }

        public void printAntRoad(TrieNode node, int depth) {
            if (node.getChildNodes() != null) {
                List<String> childs = new ArrayList<>(node.getChildNodes().keySet());
                Collections.sort(childs);
                for (String c : childs) {
                    for (int i=0; i<depth; i++) {
                        System.out.print("--");
                    }
                    System.out.println(c);
                    printAntRoad(node.getChildNodes().get(c), depth+1);
                }
            }
        }
    }
}

