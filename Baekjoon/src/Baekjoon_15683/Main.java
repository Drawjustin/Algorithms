package Baekjoon_15683;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder stb = new StringBuilder();
    private static int [][] map;
    private static int [] dx = {1,0,-1,0};
    private static int [] dy = {0,1,0,-1};
    private static int[][] cctvStrategy = {{},{0},{0,2},{0,1},{0,1,2},{0,1,2,3}};
    private static int [] cctvTurningStrategy = {-1,4,2,4,4,1};
    private static int N;
    private static int M;
    private static int result;
    private static List<int []> cctv = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        // 초기화
        result = Integer.MAX_VALUE;
        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        map = new int[N][M];
        for(int i=0 ; i<N; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(stk.nextToken());
                if(map[i][j] != 0 && map[i][j] != 6){
                    cctv.add(new int [] {map[i][j],i,j});
                }
            }
        }
        logic();
        System.out.println(result);
    }

    private static void logic() {
        dfs(0);
    }

    private static void dfs(int curIdx) {
        if(curIdx == cctv.size()){
            checkBlackSpace();
            return;
        }

        int [] curCCtv = cctv.get(curIdx);
        for(int i = 0; i<cctvTurningStrategy[curCCtv[0]]; i++) {
            setMap(curCCtv, i, true);
            dfs(curIdx + 1);
            setMap(curCCtv, i, false);
        }
    }

    private static void setMap(int[] curCCtv, int round, boolean setMap) {
        int[] checkDir = cctvStrategy[curCCtv[0]].clone();
        for (int i = 0; i < checkDir.length; i++) {
            checkDir[i] = (checkDir[i] + round) % 4;
        }
        int [] curVERTEX = new int []{curCCtv[1],curCCtv[2]};
        for (int i : checkDir) {
            int [] nxtVERTEX = new int [] {curVERTEX[0] ,curVERTEX[1]};
            while(true){
                nxtVERTEX[0] += dy[i];
                nxtVERTEX[1] += dx[i];
                if(nxtVERTEX[0] < 0 || nxtVERTEX[0] >= N || nxtVERTEX[1] < 0 || nxtVERTEX[1] >= M)
                    break;

                if(map[nxtVERTEX[0]][nxtVERTEX[1]] == 6)
                    break;

                if(map[nxtVERTEX[0]][nxtVERTEX[1]] > 0)
                    continue;

                map[nxtVERTEX[0]][nxtVERTEX[1]] += setMap ? -1 : 1;

            }
        }


    }


    private static void checkBlackSpace() {
        int blackSpace = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 0)
                    blackSpace++;
            }
        }
        result = Math.min(result,blackSpace);
    }


}
