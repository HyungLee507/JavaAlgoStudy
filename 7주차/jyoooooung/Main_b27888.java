package week7;

import java.io.*;
import java.util.*;

public class Main_b27888 {
    static int n, r;
    static Map<String, Integer> stations;
    static Map<String, Integer> features;
    static Map<Integer, Integer> featureCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        stations = new HashMap<>();
        for (int i=0; i<n; i++) {
            stations.put(br.readLine(), 0);
        }

        r = Integer.parseInt(br.readLine());
        features = new HashMap<>();
        featureCount = new HashMap<>();

        for (int i=0; i<r; i++) {
            String[] request = br.readLine().split(" ");

            if (request[0].equals("U")) {
                String stationName = request[1];
                int bit = 0;
                if (stations.containsKey(stationName)) {
                    int beforeBit = stations.get(stationName);
//                    for (int i=beforeBit; i>0; i >> 1) {
//
//                    }
                }
                if (stations.containsKey(stationName)) {
                    for (String f : request[2].split(",")) {
                        features.putIfAbsent(f, features.size()+1);
                        bit = bit | (1 << features.get(f));
                    }
                    stations.put(stationName, bit);
                    featureCount.put(bit, featureCount.getOrDefault(bit, 0) + 1);
                }

            } else { // command : G
                int bit = 0;
                for (String f : request[1].split(",")) {
                    bit = bit | (1 << features.getOrDefault(f, 0));
                }

                System.out.println(featureCount.get(bit));
            }
        }
    }
}