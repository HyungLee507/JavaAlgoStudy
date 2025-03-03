package week6;

import java.io.*;
import java.util.*;

public class Main_b4195 {
    static int F;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            F = Integer.parseInt(br.readLine());
            String[] names = new String[F+1];
            List<String> name = new ArrayList<>();
            arr = new int[F+1];
            for (int i = 1; i <= F; i++) {
                arr[i] = i;
                names[i] = br.readLine();
                String[] nameList = names[i].split(" ");
                for (String n:nameList) name

            }
            for (int i = 1; i <= F; i++) {
                String[] friends = names[i].split(" ");
                int a = name.indexOf(friends[0]);
                int b = name.indexOf(friends[1]);
                union(a,b);
                int parent = find(a);
                int count = 1;
                for (int j = 1; j <= F; j++) {
                    if (arr[j] == parent) {
                        count++;
                    }
                }
                System.out.println(count);
            }
        }


    }

    static int find(int a) {
        if (a == arr[a]) return a;
        return arr[a] = find(arr[a]);
    }

    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return;
        if (aRoot < bRoot) arr[bRoot] = aRoot;
        else arr[aRoot] = bRoot;
    }
}

