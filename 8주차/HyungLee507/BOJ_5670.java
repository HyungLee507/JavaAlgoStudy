package backjoon.B_type.study7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_5670 {

    private static class Trie {
        boolean end;
        Map<Character, Trie> next;

        public Trie() {
            end = false;
            next = new HashMap<>();
        }

        public void insert(String word) {
            int len = word.length();
            Trie temp = this;
            for (int i = 0; i < len; i++) {
                temp = temp.next.computeIfAbsent(word.charAt(i), c -> new Trie());
            }
            temp.end = true;
        }

        public int search(String word) {
            int cnt = 1;
            Trie temp = this;
            temp = temp.next.get(word.charAt(0));

            for (int i = 1; i < word.length(); i++) {
                if (temp.next.size() >= 2 || temp.next.size() == 1 && temp.end) {
                    cnt++;
                }
                char ch = word.charAt(i);
                Trie trie = temp.next.get(ch);
                temp = trie;
            }
            return cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        Trie root;
        List<String> wordList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            StringTokenizer st = null;
            while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
                int cnt = Integer.parseInt(line);
                // trie 생성 후 insert
                root = new Trie();
                wordList.clear();

                for (int i = 0; i < cnt; i++) {
                    String word = br.readLine();
                    root.insert(word);
                    wordList.add(word);
                }

                int total = 0;
                for (int i = 0; i < cnt; i++) {
                    total += root.search(wordList.get(i));
                }
                double result = (double) total / cnt;

                Formatter formatter = new Formatter();
                formatter.format("%.2f", result);
                sb.append(formatter.toString()).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb);
    }
}
