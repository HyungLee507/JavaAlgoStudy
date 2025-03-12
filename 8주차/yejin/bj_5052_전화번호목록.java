import java.io.*;

public class Main {
	static class TrieNode {
	    TrieNode[] children = new TrieNode[10];
	    boolean isEndOfNumber;
	
	    public TrieNode() {
	        isEndOfNumber = false;
	    }
	}

	static class Trie {
	    private TrieNode root;
	
	    public Trie() {
	        root = new TrieNode();
	    }
	
	    public boolean insert(String number) {
	        TrieNode node = root;
	        for (char c : number.toCharArray()) {
	            int index = c - '0';
	            if (node.children[index] == null) {
	                node.children[index] = new TrieNode();
	            }
	            node = node.children[index];
	
	            if (node.isEndOfNumber) return false; // 접두어가 이미 있는 경우
	        }
	        node.isEndOfNumber = true;
	
	        for (TrieNode child : node.children) {
	            if (child != null) return false; // 내가 접두어인 경우
	        }
	
	        return true;
	    }
	}


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());

            String[] numbers = new String[N];
            for (int i = 0; i < N; i++) {
                numbers[i] = br.readLine();
            }

            Trie trie = new Trie();
            boolean isConsistent = true;

            for (String number : numbers) {
                if (!trie.insert(number)) {
                    isConsistent = false;
                    break;
                }
            }
            System.out.println(isConsistent ? "YES" : "NO");
        }
    }
}
