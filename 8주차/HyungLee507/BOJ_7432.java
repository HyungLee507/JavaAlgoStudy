package backjoon.study.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.TreeMap;

public class BOJ_7432 {

    static class Trie {

        TreeMap<String, Trie> values;
        boolean isEnd;

        public Trie() {
            this.values = new TreeMap<>();
            isEnd = false;
        }

        public void insert(String value) {
            String[] split = value.split("\\\\");
            Trie temp = this;
            for (int i = 0; i < split.length; i++) {
                temp.values.putIfAbsent(split[i], new Trie());
                temp = temp.values.get(split[i]);
                if (i == split.length - 1) {
                    temp.isEnd = true;
                }
            }
        }
    }

    private static void dfs(StringBuilder sb, int depth, Trie trie) {

        for (Entry<String, Trie> stringTrieEntry : trie.values.entrySet()) {
            for (int i = 0; i < depth; i++) {
//                System.out.print(" ");
                sb.append(" ");
            }
//            System.out.println(stringTrieEntry.getKey());
            sb.append(stringTrieEntry.getKey()).append('\n');
            dfs(sb, depth + 1, stringTrieEntry.getValue());
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            String s = bf.readLine();
            trie.insert(s);
        }
        StringBuilder stringBuilder = new StringBuilder();
        dfs(stringBuilder, 0, trie);
        System.out.println(stringBuilder);
        bf.close();
    }
}
