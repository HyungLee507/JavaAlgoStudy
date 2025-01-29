import java.util.*;
import java.io.*;

public class Main1405 {
    static int n;
    static double probEast, probWest, probSouth, probNorth;
    static double result;
    static boolean[][] map = new boolean[40][40];

    static void findProb(int x, int y, int depth, double prob) {
        if(depth==n){
            result+=prob;
            return;
        }

        if(!map[x+1][y]){
            map[x+1][y] = true;
            findProb(x+1, y, depth+1, prob*probSouth);
            map[x+1][y] = false;
        }
        if(!map[x-1][y]){
            map[x-1][y] = true;
            findProb(x-1, y, depth+1, prob*probNorth);
            map[x-1][y] = false;
        }
        if(!map[x][y+1]){
            map[x][y+1] = true;
            findProb(x, y+1, depth+1, prob*probEast);
            map[x][y+1] = false;
        }
        if(!map[x][y-1]) {
            map[x][y - 1] = true;
            findProb(x , y-1, depth+1, prob*probWest);
            map[x][y-1] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        probEast = Integer.parseInt(st.nextToken())/100.0;
        probWest = Integer.parseInt(st.nextToken())/100.0;
        probSouth = Integer.parseInt(st.nextToken())/100.0;
        probNorth = Integer.parseInt(st.nextToken())/100.0;

        map[20][20] = true;
        findProb(20,20,0,1.0);

        System.out.println(result);
    }
}
