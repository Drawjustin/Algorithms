package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Codetree_2024_상반기_오후_1번_마법의숲탐색 {
    static int R, C, K;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0}; // 북 동 남 서
    static List<Spirit> list = new ArrayList<>();
    static int result = 0;
    static int idx = 0;
    static int[] dirOrder = {2, 3, 1};
    static class Spirit {
        int y;
        int x;
        int ci;
        int di;

        public Spirit(int y, int x, int ci, int di) {
            this.y = y;
            this.x = x;
            this.ci = ci;
            this.di = di;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        R = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());

        map = new int[R + 3][C + 1];
        for (int i = 0; i < K; i++) {
            stk = new StringTokenizer(br.readLine());
            int tempCi = Integer.parseInt(stk.nextToken());
            int tempDi = Integer.parseInt(stk.nextToken());
            list.add(new Spirit(1, tempCi, tempCi, tempDi));
        }

        for (Spirit spirit : list) {
            idx++;
            boolean isMoved;
            do {
                isMoved = false;
                for (int i = 0; i < 3; i++) {
                    if (canMove(spirit, dirOrder[i])) {
                        move(spirit, dirOrder[i]);
                        isMoved = true;
                        break;
                    }
                }
                // 이번턴에 이동하지 않았거나, 이동했는데 맵의 마지막에 도착했을경우 끝냄
            } while (isMoved && !isMapEnd(spirit.y));
            if (isMapOut(spirit.y)) {
                for (int i = 1; i <= R + 2; i++) {
                    Arrays.fill(map[i], 0);
                }
                continue;
            }
            mapDraw(spirit);
            result += spiritMove(spirit);
        }
        System.out.println(result);
    }

    private static int spiritMove(Spirit spirit) {
        int result = 0;
        boolean[][] isVisited = new boolean[R + 3][C + 1];
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{spirit.y, spirit.x});
        isVisited[spirit.y][spirit.x] = true;

        while (!deque.isEmpty()) {
            int[] curTemp = deque.poll();

            if (curTemp[0] > result)
                result = curTemp[0];

            if (map[curTemp[0]][curTemp[1]] > 10000) {
                for (int i = 0; i < 4; i++) {
                    int y = curTemp[0] + dy[i];
                    int x = curTemp[1] + dx[i];

                    if (y < 1 || y > R + 2 || x < 1 || x > C)
                        continue;

                    if (isVisited[y][x])
                        continue;

                    if (map[y][x] == 0)
                        continue;
                    deque.add(new int[]{y, x});
                    isVisited[y][x] = true;
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    int y = curTemp[0] + dy[i];
                    int x = curTemp[1] + dx[i];

                    if (y < 1 || y > R + 2 || x < 1 || x > C)
                        continue;

                    if (isVisited[y][x])
                        continue;

                    if (map[y][x] != map[curTemp[0]][curTemp[1]] && map[y][x] - map[curTemp[0]][curTemp[1]] != 10000)
                        continue;

                    deque.add(new int[]{y, x});
                    isVisited[y][x] = true;
                }
            }


        }

        return result-2;
    }

    private static void mapDraw(Spirit spirit) {
        map[spirit.y][spirit.x] = idx;
        for (int i = 0; i < 4; i++) {
            map[spirit.y + dy[i]][spirit.x + dx[i]] = idx;
        }
        map[spirit.y+ dy[spirit.di]][spirit.x + dx[spirit.di]] = 10000 + idx;
    }

    private static void move(Spirit spirit, int direction) {
        switch (direction) {
            case 2 -> spirit.y += 1;
            case 3 -> {
                spirit.di = spirit.di - 1 >= 0 ? spirit.di - 1 : 3;
                spirit.x -= 1;
            }
            case 1 -> {
                spirit.di = spirit.di + 1 < 4 ? spirit.di + 1 : 0;
                spirit.x += 1;
            }
        }
    }

    private static boolean isMapOut(int y) {
        return y < 4;
    }

    private static boolean isMapEnd(int y) {
        return y == R + 1;
    }

    private static boolean canMove(Spirit spirit, int direction) {

        switch (direction) {
            case 2 -> {
                // 남쪽 이동 시 초록색 3개가 빈곳 아니면
                return map[spirit.y + 2][spirit.x] == 0 && map[spirit.y + 1][spirit.x - 1] == 0 && map[spirit.y + 1][spirit.x + 1] == 0;
            }
            case 3 -> {
                // 서쪽 벽이 막혀있거나
                if (spirit.x - 2 < 1) {
                    return false;
                }
                // 서쪽 초록색 5개가 빈곳이 아니면
                return map[spirit.y][spirit.x - 2] == 0 && map[spirit.y - 1][spirit.x - 1] == 0 && map[spirit.y + 1][spirit.x - 1] == 0 && map[spirit.y + 2][spirit.x - 1] == 0 && map[spirit.y + 1][spirit.x - 2] == 0;
            }
            case 1 -> {
                // 동쪽 벽이 막혀있거나
                if (spirit.x + 2 > C) {
                    return false;
                }
                // 동쪽 초록색 5개가 빈곳이 아니면
                return map[spirit.y][spirit.x + 2] == 0 && map[spirit.y - 1][spirit.x + 1] == 0 && map[spirit.y + 1][spirit.x + 1] == 0 && map[spirit.y + 2][spirit.x + 1]  == 0 && map[spirit.y + 1][spirit.x + 2] == 0;
            }
        }
        return false;
    }

}
