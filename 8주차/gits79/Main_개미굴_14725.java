package boj;

import java.io.*;
import java.util.*;

public class Main_개미굴_14725 {

    static class Node {
        // 자식노드
        // 기존 HashMap에서 자식 노드를 삽입 할 때 자동 정렬이 되는 TreeMap으로 변경
        Map<String, Node> child = new TreeMap<>();

        boolean endOfword;

    }

    static class Trie {
        // Trie의 기본 루트 노드 생성
        Node rootNode = new Node();

        // 문자열 저장
        void insert(String[] arr) {
            // 항상 루트부터 시작
            Node node = this.rootNode;

            // 문자열이 자식노드 중에 있는지 체크
            // 없으면 자식노드 새로 생성
            for (int i = 0; i < arr.length; i++) {
                node = node.child.computeIfAbsent(arr[i], key -> new Node());
            }

        }

        static void dfs(Node node, int depth) {
            // 자식 노드를 가져옴 이미 정렬 되어 있기 때문에 정렬할 필요 없음
            for (String key : node.child.keySet()) {
                for (int i = 0; i < depth; i++) System.out.print("--");
                System.out.println(key);
                dfs(node.child.get(key), depth + 1);
            }
        }

        public boolean delete(Node node, String string, int index){
            char c = string.charAt(index);

            if (!node.child.containsKey(c)) {
                return false;
            }

            Node cur = node.child.get(c);
            index++;

            if (index == string.length()) {
                if (!cur.endOfword) {
                    return false;
                }

                cur.endOfword = false;

                // 현재 노드에서, 자식 노드가 비었는지 확인
                // 안 비워있다면, 그냥 마지막 알파벳을 false 처리하고, 끝내면 된다
                if (cur.child.isEmpty()) {
                    node.child.remove(c);    // node는 current 노드의 부모 노드 (current 노드를 삭제)
                }
            } else {
                if (!this.delete(cur, string, index)) {
                    return false;
                }

                if (!cur.endOfword && cur.child.isEmpty()) {
                    node.child.remove(c);
                }
            }
            return true;
        }


    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Trie trie = new Trie();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            String[] arr = new String[K];
            for (int j = 0; j < K; j++) {
                arr[j] = st.nextToken();
            }
            trie.insert(arr);
        }

        Node node = trie.rootNode;
        trie.dfs(node, 0);

    }
}
