import java.io.*;
import java.util.*;

public class Main3079 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        long start = 0;
        long end = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            end = Math.max(end, arr[i]);
        }
        end *= m;
        long result = Long.MAX_VALUE;

        while(start <= end) {
            long mid = (start+end)/2;
            long temp = 0;
            for(int i = 0; i < n; i++) {
                temp += mid/arr[i];
                if(temp >= m) break;
            }
            if(temp >= m) {
                result = mid;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }

        System.out.println(result);
    }
}
