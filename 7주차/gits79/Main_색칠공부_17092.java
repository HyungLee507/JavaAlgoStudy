package boj;

import java.io.*;
import java.util.*;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // contains() 는 내부적으로 equals()를 사용하여 qlrygksmsep
    // equals() 를 오버라이드하지 않으면 객체의 메모리 주소를 비교함
    // 그래서 다른 객체를 인식해서 같은 좌표라도 false 반환
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class Main_색칠공부_17092 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        Set<Point> blacks = new HashSet<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            blacks.add(new Point(x, y));
        }

        long[] count = new long[10];

        // 이미 센 3×3 부분 모눈종이의 중심을 저장하기 위한 Set
        Set<Point> centers = new HashSet<>();

        // 각 검정색 칸을 중심으로 3×3 부분 모눈종이의 중심을 확인
        int[] dx = {-1, 0, 1};
        int[] dy = {-1, 0, 1};

        for (Point p : blacks) {
            for (int i : dx) {
                for (int j : dy) {
                    int centerX = p.x + i;
                    int centerY = p.y + j;
                    Point center = new Point(centerX, centerY);

                    // 중심이 이미 확인되었거나, 범위를 벗어나면 건너뜀
                    if (centers.contains(center) || centerX < 2 || centerX > h - 1 || centerY < 2 || centerY > w - 1) {
                        continue;
                    }

                    // 현재 중심을 기준으로 3×3 부분 모눈종이의 검정색 칸 개수 세기
                    int blackCount = 0;
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            if (blacks.contains(new Point(centerX + x, centerY + y))) {
                                blackCount++;
                            }
                        }
                    }

                    // 3 x 3 사이즈에서 나온 검은색 개수의 칸에 해당하는 배열의 크기 하나증가
                    count[blackCount]++;
                    // 중심점도 추가로 관리해서 이미 탐색한 중심점이라면 패스할 수 있도록
                    centers.add(center);
                }
            }
        }

        // 전체 3×3 부분 모눈종이의 개수
        long total = (long) (h - 2) * (w - 2);

        long sum = 0;
        for (int i = 1; i < 10; i++) {
            sum += count[i];
        }

        // 검정색 칸이 0개인 3×3 부분 모눈종이의 개수
        count[0] = total - sum;


        for (long c : count) {
            System.out.println(c);
        }
    }
}
