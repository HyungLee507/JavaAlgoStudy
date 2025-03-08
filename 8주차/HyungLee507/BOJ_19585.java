package backjoon.B_type.study7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_19585 {
    static int C, N, Q;
    static Trie colors;
    static Set<String> nicknames;

    private static class Trie {
        boolean end;
        Trie[] next;

        public Trie() {
            this.end = false;
            this.next = new Trie[26];
        }

        public void insert(String word) {
            Trie temp = this;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (temp.next[idx] == null) {
                    temp.next[idx] = new Trie();
                }
                temp = temp.next[idx];
            }
            temp.end = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        colors = new Trie();
        nicknames = new HashSet<>();
        for (int i = 0; i < C; i++) {
            colors.insert(bf.readLine());
        }
        for (int i = 0; i < N; i++) {
            nicknames.add(bf.readLine());
        }
        Q = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            String team = bf.readLine();
            if (canRewarding(colors, team)) {
                sb.append("Yes").append('\n');
            } else {
                sb.append("No").append('\n');
            }
        }
        System.out.println(sb);
        bf.close();
    }

    private static boolean canRewarding(Trie trie, String team) {
        Trie temp = trie;
        for (int i = 0; i < team.length(); i++) {
            char ch = team.charAt(i);
            if (temp.end) {
                String nickname = team.substring(i);
                if (nicknames.contains(nickname)) {
                    return true;
                }
            }
            int idx = ch - 'a';
            if (temp.next[idx] != null) {
                temp = temp.next[idx];
            } else {
                return false;
            }
        }
        return false;
    }
}
