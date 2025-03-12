package boj;

import java.io.*;
import java.util.*;

public class Main_전화번호목록_5052 {

    static class Node {
        // 자식노드
        Map<Character, Node> child = new HashMap<>();
        // 단어의 끝인지 아닌지 체크
        boolean endOfword;
    }

    static class Trie {
        // Trie의 기본 루트 노드 생성
        Node rootNode = new Node();

        // 문자열 저장
        boolean insert(String str) {
            // 항상 루트부터 시작
            Node node = this.rootNode;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                // 가져온 노드가 마지막 노드라면 이미 존재하는 접두사이기 때문에 false
                if (node.endOfword) return false;

                // 문자열의 각 단어마다 가져와서 자식노드 중에 있는지 체크
                // 없으면 자식노드 새로 생성
                node = node.child.computeIfAbsent(c, key -> new Node());
            }
            // 저장 할 문자열의 마지막 단어의 노드에 끝임을 명시
            node.endOfword = true;
            return true;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int T = Integer.parseInt(st.nextToken());
        for (int tc = 0; tc < T; tc++) {
            Trie trie = new Trie();
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            List<String> lst = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                String word = br.readLine();
                lst.add(word);
            }

            Collections.sort(lst);
            String answer = "YES";
            for (String word : lst) {
                if (!trie.insert(word)) {
                    answer = "NO";
                }
            }

            System.out.println(answer);
        }

    }
}
