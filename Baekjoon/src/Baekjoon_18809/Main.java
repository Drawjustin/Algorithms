package Baekjoon_18809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int result = 0;
    static int [] dy = {0,1,0,-1};
    static int [] dx = {1,0,-1,0};
    static int N,M,G,R,MAXLEVEL;
    static int [][] map;
    static List<int []> goodMap = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // 0 호수 1 배불땅 2 배가땅 3 초배 4 빨배 5 꽃
        init();
        dfs(0,0);
        System.out.println("result = " + result);

    }

    private static void dfs(int level, int startIdx) {
        if(level == MAXLEVEL){
            bfs();
            return;
        }

        if(startIdx >= goodMap.size()) {
            return;
        }

        int[] curVERTEX = goodMap.get(startIdx);
        if(G > 0) {
            map[curVERTEX[0]][curVERTEX[1]] = 3;
            G--;
            dfs(level + 1, startIdx + 1);
            map[curVERTEX[0]][curVERTEX[1]] = 2;
            G++;
        }

        if(R > 0) {
            map[curVERTEX[0]][curVERTEX[1]] = 4;
            R--;
            dfs(level + 1, startIdx + 1);
            map[curVERTEX[0]][curVERTEX[1]] = 2;
            R++;
        }

        dfs(level, startIdx + 1);
    }

    private static void bfs() {

        int [][] tempMap = copyMap(map);
        boolean [][] isVisited = new boolean[N][M];
        Deque<int []> deque = new ArrayDeque<>();
        initMap(tempMap, deque, isVisited);
        while(!deque.isEmpty()){
            int [] curTemp = deque.poll();

            for (int i = 0; i < 4; i++) {
                int nxtY = curTemp[0] + dy[i];
                int nxtX = curTemp[1] + dx[i];

                if(nxtY < 0 || nxtY >= N || nxtX < 0 || nxtX >= M)
                    continue;
                if(isVisited[nxtY][nxtY])
            }

        }
        result = Math.max(result,findFlower(tempMap));

    }

    private static int findFlower(int[][] tempMap) {
        int tempResult = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(tempMap[i][j] == 5)
                    tempResult++;
            }
        }
        return tempResult;
    }

    private static void initMap(int [][] tempMap, Deque<int []> deque, boolean [][] isVisited) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(tempMap[i][j] == 3 || tempMap[i][j] == 4){
                    deque.add(new int[]{i,j,0});
                    isVisited[i][j] = true;
                }
            }
        }
    }

    private static int[][] copyMap(int[][] map) {
        int[][] tempMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            tempMap[i] = Arrays.copyOf(map[i], M);
        }
        return tempMap;
    }

    private static void init() throws IOException{
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        G = Integer.parseInt(stk.nextToken());
        R = Integer.parseInt(stk.nextToken());
        MAXLEVEL = G+R;
        map = new int[N][M];
        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(stk.nextToken());
                if(map[i][j] == 2)
                    goodMap.add(new int []{i,j});
            }
        }
    }

}
