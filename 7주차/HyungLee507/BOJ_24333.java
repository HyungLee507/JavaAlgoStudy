import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static Map<String, Integer> featureMap;
    static Map<String, Integer> features;
    static Map<Integer, Integer> freq;
    static int total;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        featureMap = new HashMap<>();
        features = new HashMap<>();
        freq = new HashMap<>();
        total = 0;

        int n = Integer.parseInt(bf.readLine());
        for (int i = 0; i < n; i++) {
            String station = bf.readLine();
            featureMap.put(station, 0);
            freq.put(0, freq.getOrDefault(0, 0) + 1);
        }

        int m = Integer.parseInt(bf.readLine());
        for (int i = 0; i < m; i++) {
            String temp = bf.readLine();
            String content = temp.substring(2);
            if (temp.charAt(0) == 'U') {
                String[] splitContent = content.split(" ");
                String station = splitContent[0];
                String[] splitFeatures = splitContent[1].split(",");
                int oldMask = featureMap.getOrDefault(station, 0);
                freq.put(oldMask, freq.get(oldMask) - 1);

                int newMask = 0;
                for (String feature : splitFeatures) {
                    if (!features.containsKey(feature)) {
                        features.put(feature, total++);
                    }
                    int index = features.get(feature);
                    newMask |= (1 << index);
                }
                featureMap.put(station, newMask);
                freq.put(newMask, freq.getOrDefault(newMask, 0) + 1);
            } else {
                int queryMask = getHashValue(content);
                if (queryMask == -1) {
                    System.out.println(0);
                } else {
                    int answer = querySupersets(queryMask);
                    System.out.println(answer);
                }
            }
        }
        bf.close();
    }

    private static int querySupersets(int queryMask) {
        int fullMask = (1 << total) - 1;
        int r = fullMask ^ queryMask;
        int sum = 0;

        for (int s = r; ; s = (s - 1) & r) {
            int candidate = queryMask | s;
            sum += freq.getOrDefault(candidate, 0);
            if (s == 0) break;
        }
        return sum;
    }

    private static int getHashValue(String content) {
        int maskingValue = 0;
        String[] split = content.split(",");
        for (String s : split) {
            if (!features.containsKey(s)) {
                return -1;
            }
            maskingValue |= (1 << features.get(s));
        }
        return maskingValue;
    }
}
