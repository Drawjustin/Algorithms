package Baekjoon_15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Curve {
        int y;
        int x;
        int d;
        int g;

        public Curve(int y, int x, int d, int g) {
            this.y = y;
            this.x = x;
            this.d = d;
            this.g = g;
        }
    }

    static int[] dy = {0, -1, 0, 1}; // 0:→, 1:↑, 2:←, 3:↓
    static int[] dx = {1, 0, -1, 0};
    static int N;
    static List<Curve> list = new ArrayList<>();
    static int[][] map = new int[101][101];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int d = Integer.parseInt(stk.nextToken());
            int g = Integer.parseInt(stk.nextToken());
            list.add(new Curve(y, x, d, g));
        }
        for (int i = 0; i < list.size(); i++) {
            logic(list.get(i));
        }
        int result = checkSquare();

        System.out.println(result);
    }

    private static int checkSquare() {
        int tempResult = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] == 1 && map[i + 1][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j + 1] == 1)
                    tempResult++;
            }
        }
        return tempResult;
    }


    private static void logic(Curve curve) {
        List<Integer> directions = new ArrayList<>();
        directions.add(curve.d);

        for (int i = 0; i < curve.g; i++) {
            int size = directions.size();
            for (int j = size - 1; j >= 0; j--) {
                directions.add((directions.get(j) + 1) % 4);
            }
        }


        int nxtY = curve.y;
        int nxtX = curve.x;
        map[nxtY][nxtX] = 1;

        for (int curD : directions) {
            nxtY += dy[curD];
            nxtX += dx[curD];
            if (nxtY >= 0 && nxtY <= 100 && nxtX >= 0 && nxtX <= 100) {
                map[nxtY][nxtX] = 1;
            }
        }
    }
}
