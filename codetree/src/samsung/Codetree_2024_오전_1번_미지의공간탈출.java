package samsung;

import java.io.*;
import java.util.*;

public class Codetree_2024_오전_1번_미지의공간탈출 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static int[][] cube;
    // [0] = ri, [1] = ci, [2] = di, [3] = vi, [4] = nowCount
    static Deque<int[]> blackhole = new ArrayDeque<>();
    static int N, M, F;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static StringTokenizer stk;
    static int result = -1;
    static int [] escapeVERTEX;

    public static void main(String[] args) throws IOException {
        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        F = Integer.parseInt(stk.nextToken());
        //큐브,맵 초기화
        mapAndCubeInit();

        //큐브 탈출
        if(cubeEscape()) {
            for (int i = 0; i < M * 3; i++) {
                for (int j = 0; j < M * 3; j++) {
                    System.out.print(cube[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("result = " + result);
            result++;
            //이상현상 진행
            progressBlackhole(result);
            //맵 탈출
            if (!mapEscape(result))
                result = -1;
        }
        System.out.println(result);
    }

    private static void printMap(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println("=============");
        System.out.println("=============");
    }
    private static void mapAndCubeInit() throws IOException {
        map = new int[N + 1][N + 1];
        cube = new int[M * 3 + 1][M * 3 + 1];
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        for (int i = 0; i < M * 3; i++) {
            for (int j = 0; j < M * 3; j++) {
                cube[i][j] = -5;
            }
        }


        // 큐브 동
        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cube[2 * M - 1 - j][2 * M + i] = Integer.parseInt(stk.nextToken());
            }
        }
        // 큐브 서
        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cube[M + j][M - 1 - i] = Integer.parseInt(stk.nextToken());
            }
        }
        // 큐브 남
        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cube[M * 2 + i][M + j] = Integer.parseInt(stk.nextToken());
            }
        }
        // 큐브 북
        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cube[M - 1 - i][2 * M - 1 - j] = Integer.parseInt(stk.nextToken());
            }
        }
        // 큐브 중앙
        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cube[M + i][M + j] = Integer.parseInt(stk.nextToken());
            }
        }

        for (int i = 0; i < F; i++) {
            stk = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(stk.nextToken());
            int x = Integer.parseInt(stk.nextToken());
            int di = Integer.parseInt(stk.nextToken());
            if(di == 1)
                di = 2;
            else if(di == 2)
                di = 1;
            int vi = Integer.parseInt(stk.nextToken());
            blackhole.add(new int [] {y,x,di,vi,0});
            map[y][x] = 1;
        }

        findEscapeWall();


    }

    private static void findEscapeWall() {
        escapeVERTEX = new int[3];
        int[] startTimeWall = new int[2];
        outer:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        if (i + dy[k] < 0 || i + dy[k] >= N || j + dx[k] < 0 || j + dx[k] >= N)
                            continue;
                        if (map[i + dy[k]][j + dx[k]] == 3) {
                            escapeVERTEX[0] = i;
                            escapeVERTEX[1] = j;
                            escapeVERTEX[2] = k;
                            break outer;
                        }

                    }
                }
            }
        }

        outer:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 3) {
                    startTimeWall[0] = i;
                    startTimeWall[1] = j;
                    break outer;
                }
            }
        }

        int height = Math.abs(escapeVERTEX[0] - startTimeWall[0]);
        int width = Math.abs(escapeVERTEX[1] - startTimeWall[1]);
        switch (escapeVERTEX[2]) {
            case 0:
                if(cube[M + height][0] == 0)
                    cube[M + height][0] = -1;
                break;
            case 1:
                if(cube[0][M + width] == 0)
                    cube[0][M + width] = -1;
                break;
            case 2:
                if(cube[M + height][3 * M - 1] == 0)
                    cube[M + height][3 * M - 1] = -1;
                break;
            case 3:
                if(cube[3 * M - 1][M + width] == 0)
                    cube[3 * M - 1][M + width] = -1;
                break;

        }


    }

    private static boolean cubeEscape() {
        int[] startLocation = new int[2];
        outer:
        for (int i = 0; i < M * 3; i++) {
            for (int j = 0; j < M * 3; j++) {
                if (cube[i][j] == 2) {
                    startLocation[0] = i;
                    startLocation[1] = j;
                    break outer;
                }
            }
        }

        int[] escapeLocation = new int[2];
        outer:
        for (int i = 0; i < M * 3; i++) {
            for (int j = 0; j < M * 3; j++) {
                if (cube[i][j] == -1) {
                    escapeLocation[0] = i;
                    escapeLocation[1] = j;
                    break outer;
                }
            }
        }

        Deque<int[]> deque = new ArrayDeque<>();
        boolean[][] isVistied = new boolean[M * 3][M * 3];
        deque.add(new int[]{startLocation[0], startLocation[1], 0});
        isVistied[startLocation[0]][startLocation[1]] = true;
        while (!deque.isEmpty()) {
            int[] curLocation = deque.poll();

            System.out.println("Y : " + curLocation[0] + " X : " + curLocation[1] + " time : " + curLocation[2]);
            for (int i = 0; i < 4; i++) {
                int nxtY = curLocation[0] + dy[i];
                int nxtX = curLocation[1] + dx[i];

                if (0 > nxtY || nxtY >= M * 3 || 0 > nxtX || nxtX >= M * 3)
                    continue;

                if (cube[nxtY][nxtX] == -5) {
                    int[] nxtLocation = findNxtLocation(curLocation[0], curLocation[1], i);
                    nxtY = nxtLocation[0];
                    nxtX = nxtLocation[1];
                }
                if (cube[nxtY][nxtX] == 1)
                    continue;

                if (nxtY == escapeLocation[0] && nxtX == escapeLocation[1]) {
                    result = curLocation[2] + 1;
                    return true;
                }

                if (isVistied[nxtY][nxtX])
                    continue;

                deque.add(new int[]{nxtY, nxtX, curLocation[2] + 1});
                isVistied[nxtY][nxtX] = true;

            }


        }
        return false;
    }

    private static int[] findNxtLocation(int curY, int curX, int dir) {
        if (curY + dy[dir] < M && curX + dx[dir] < M) {
            return new int[]{curX, curY};
        } else if (curY + dy[dir] < M && curX + dx[dir] >= 2 * M) {
            return new int[]{3 * M - 1 - curX, 3 * M - 1 - curY};
        } else if (curY + dy[dir] >= 2 * M && curX + dx[dir] < M) {
            return new int[]{3 * M - 1 - curX, 3 * M - 1 - curY};
        } else if (curY + dy[dir] >= 2 * M && curX + dx[dir] >= 2 * M) {
            return new int[]{curX, curY};
        }
        return new int[]{-1, -1};
    }


    private static void progressBlackhole(int curTimes) {

        for (int i = 0; i < curTimes; i++) {
            int curblackholeSize = blackhole.size();
            for (int j = 0; j < curblackholeSize; j++) {
                int [] curHole = blackhole.poll();

                if(curHole[3] > curHole[4] + 1){
                    blackhole.add(new int []{curHole[0],curHole[1],curHole[2],curHole[3],curHole[4]+1});
                    continue;
                }
                if(0 > curHole[0]+dy[curHole[2]] || curHole[0] + dy[curHole[2]] >= N ||
                   0 > curHole[1] + dx[curHole[2]] || curHole[1] + dx[curHole[2]] >= N)
                    continue;

                if(map[curHole[0]+dy[curHole[2]]][curHole[1]+dx[curHole[2]]] == 1 || map[curHole[0]+dy[curHole[2]]][curHole[1]+dx[curHole[2]]] == 3 || map[curHole[0]+dy[curHole[2]]][curHole[1]+dx[curHole[2]]] == 4){
                    continue;
                }
                map[curHole[0]+dy[curHole[2]]][curHole[1]+dx[curHole[2]]] = 1;
                blackhole.add(new int []{curHole[0]+dy[curHole[2]],curHole[1]+dx[curHole[2]],curHole[2],curHole[3],0});
            }
        }
    }

    private static boolean mapEscape(int curTimes) {
        Deque<int []> deque = new ArrayDeque<>();
        boolean[][] isVisited = new boolean [N+1][N+1];

        deque.add(new int [] {escapeVERTEX[0],escapeVERTEX[1],curTimes});
        isVisited[escapeVERTEX[0]][escapeVERTEX[1]] = true;


        if(map[escapeVERTEX[0]][escapeVERTEX[1]] == 4){
            return true;
        }

        while(!deque.isEmpty()){
            int dequeSize = deque.size();
            progressBlackhole(1);
            for (int i = 0; i < dequeSize; i++) {
                int[] curLocation = deque.poll();

                for (int j = 0; j < 4; j++) {
                    int y = curLocation[0] + dy[j];
                    int x = curLocation[1] + dx[j];


                    if (0 > y || y >= N || 0 > x || x >= N)
                        continue;

                    if (map[y][x] == 4) {
                        result = curLocation[2] + 1;
                        return true;
                    }
                    if (isVisited[y][x])
                        continue;
                    if (map[y][x] == 0) {
                        map[y][x] = 2;
                        deque.add(new int[]{y, x, curLocation[2] + 1});
                        isVisited[y][x] = true;
                    }
                }
            }
        }
        return false;
    }
}