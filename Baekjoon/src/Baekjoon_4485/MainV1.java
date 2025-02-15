package Baekjoon_4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainV1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int [][] map;
    static int N;
    public static void main(String[] args) throws IOException {
        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0)
                break;

            map = new int[N+1][N+1];

            for (int i = 0; i <= N; i++) {
                Arrays.fill(map[i],0x7000000);
            }

            for (int i = 1; i <= N; i++) {
                StringTokenizer stk = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    map[i][j] = Integer.parseInt(stk.nextToken());
                }
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(i == 1 && j == 1)
                        continue;
                    map[i][j] = Math.min(map[i-1][j] + map[i][j], map[i][j-1] + map[i][j]);
                }
            }
            System.out.println(map[N][N]);
        }

    }
}
