import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 역을 저장하는 맵
        Map<String, Long> station = new HashMap<>();
        // 특징을 저장하는 맵
        Map<String, Integer> feature = new HashMap<>();
        int idx = 0;

        // 역 개수 입력
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            station.put(str, 0L);
        }

        // 요청 개수 입력
        int R = Integer.parseInt(br.readLine());
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String oper = st.nextToken();

            if (oper.equals("U")) { // 특징 추가
                String key = st.nextToken(); // 역 이름
                String temp = st.nextToken(); // 특징 목록

                // 기존 특징 비트마스크 가져오기
                long mask = station.get(key);

                // 특징을 ',' 기준으로 분리하여 추가
                st = new StringTokenizer(temp, ",");
                while (st.hasMoreTokens()) {
                    String value = st.nextToken();

                    // 특징이 처음 등장하면 새로운 비트 인덱스 할당
                    int val = feature.getOrDefault(value, idx++);
                    feature.put(value, val); // 특징 등록

                    // 역의 비트마스크 갱신 (비트 OR 연산)
                    mask |= (1L << val);
                }

                // 역 정보 업데이트
                station.put(key, mask);

            } else if (oper.equals("G")) {
                String key = st.nextToken(); // 찾을 특징 목록


                st = new StringTokenizer(key, ",");
                long requiredMask = 0L;
                boolean validQuery = true;

                while (st.hasMoreTokens()) {
                    String value = st.nextToken();
                    if (!feature.containsKey(value)) {
                        validQuery = false; // 존재하지 않는 특징이면 0 출력
                        break;
                    }
                    requiredMask |= (1L << feature.get(value));
                }

                if (!validQuery) {
                    System.out.println(0);
                    continue;
                }

                // 비트 연산으로 모든 역 탐색
                int count = 0;
                for (String stationName : station.keySet()) {
                    if ((station.get(stationName) & requiredMask) == requiredMask) {
                        count++;
                    }
                }

                System.out.println(count);
            }
        }
    }
}
