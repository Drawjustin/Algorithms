package Baekjoon_15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainV1 {
    static int N,M,H;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int [][] map = new int[32][32];
    public static void main(String[] args) throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        H = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            map[b][a] = b+1;
            map[b+1][a] = b;
        }

        int result = -1;
        for (int i = 0; i < H*(N-1) - M; i++) {
            if(manager(0,i)) {
                result = i;
                break;
            }
        }
        if(result > 3)
            System.out.println(-1);
        else{
            System.out.println(result);
        }
    }
    private static boolean manager(int i, int target) throws IOException {

        if(i == target)
            return solve();
        for (int j = 1; j <= N; j++) {
            for (int k = 1; k <= H; k++) {
                if(isOK(new int []{j,k})){
                    map[j][k] = j+1;
                    map[j+1][k] = j;
                    if(manager(i+1, target)) {
                        return true;
                    }
                    map[j][k] = 0;
                    map[j+1][k] = 0;

                }
            }
        }
        return false;
    }


    private static boolean solve() throws IOException {
        for (int i = 1; i <= H; i++) {
            int [] start = new int []{i,1};

            while(start[1] <= H) {
                check(start);
                start[1]++;
            }
            if(start[0] != i) {
                return false;
            }
        }
        return true;
    }

    private static void check(int[] start) {
        if(map[start[0]][start[1]] != 0){
            start[0] = map[start[0]][start[1]];
        }
    }
    private static boolean isOK(int[] target) {
        return map[target[0]][target[1]] == 0 &&
                map[target[0]][target[1]+1] == 0 &&
                (target[1] == 1 || map[target[0]][target[1]-1] == 0);
    }
}
