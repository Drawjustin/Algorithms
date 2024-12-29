package Baekjoon_2206;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    static int [] dx = {1,0,-1,0};
    static int [] dy = {0,1,0,-1};
    static int N;
    static int M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
         N = Integer.parseInt(stk.nextToken());
         M = Integer.parseInt(stk.nextToken());
         map = new int [N][M];
        int result = -1;

        init();
        result = logic();
        System.out.println(result);


    }

    private static void init() throws IOException {
        for(int i=0; i<N; i++){
            String temp = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = temp.charAt(j) - '0';
            }
        }
    }

    private static int logic() {
        int [] start = new int[]{0,0,1};
        int [] target = new int[]{N-1,M-1};
        boolean [][][] isVisited = new boolean[N][M][2];
        isVisited[start[0]][start[1]][0] = true;
        Deque<int []> deque = new ArrayDeque<>();
        deque.add(new int []{0,0,1,1});
        while(!deque.isEmpty()){
            int [] curVertex = deque.poll();

            if(curVertex[0] == N-1 && curVertex[1] == M-1){
                return curVertex[3];
            }

            for(int i=0; i<4; i++){
                int nxtY = curVertex[0] + dy[i];
                int nxtX = curVertex[1] + dx[i];


                if(0> nxtY || nxtY >= N || 0 > nxtX || nxtX >= M)
                    continue;

                if(curVertex[2] == 0){
                    if(isVisited[nxtY][nxtX][0])
                        continue;

                    if(map[nxtY][nxtX] == 1){
                        continue;
                    }
                    deque.add(new int [] {nxtY,nxtX,curVertex[2],curVertex[3]+1});
                    isVisited[nxtY][nxtX][0] = true;

                }else{
                    if(isVisited[nxtY][nxtX][1])
                        continue;

                    if(map[nxtY][nxtX] == 1){
                        deque.add(new int [] {nxtY,nxtX,0,curVertex[3]+1});
                        isVisited[nxtY][nxtX][0] = true;
                    }
                    else{
                        deque.add(new int [] {nxtY,nxtX,curVertex[2],curVertex[3]+1});
                        isVisited[nxtY][nxtX][1] = true;
                    }
                }

            }

        }
        return -1;
    }


}
