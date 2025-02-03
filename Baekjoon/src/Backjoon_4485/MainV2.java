package Backjoon_4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class MainV2 {
    static int sum,N;
    static int [] dx = {1,0,-1,0};
    static int [] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int t = 1;
        while(N != 0){
            sum = 0;
            int [][]map = new int[N+1][N+1];
            int [][]value = new int[N+1][N+1];
            for(int i=0; i<N; i++)
                Arrays.fill(value[i],Integer.MAX_VALUE);
            for(int i=0; i<N; i++){
                StringTokenizer stk = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(stk.nextToken());
                }
            }
            bfs(map,value);
            System.out.println("Problem "+ +t++ +": "+value[N-1][N-1]);

            N = Integer.parseInt(br.readLine());
        }
    }
    private static void bfs(int [][] map,int [][]value){
        Deque<int []> queue = new ArrayDeque<>();
        queue.add(new int []{0,0,map[0][0]});
        while(!queue.isEmpty()){
            int [] temp = queue.poll();

            for(int i=0; i<4; i++){
                if(checked(temp[0]+dy[i],temp[1]+dx[i]) && value[temp[0]+dy[i]][temp[1]+dx[i]] > temp[2]+map[temp[0]+dy[i]][temp[1]+dx[i]]){
                    value[temp[0]+dy[i]][temp[1]+dx[i]] = temp[2]+map[temp[0]+dy[i]][temp[1]+dx[i]];
                    queue.add(new int [] {temp[0]+dy[i],temp[1]+dx[i],temp[2]+map[temp[0]+dy[i]][temp[1]+dx[i]]});
                }
            }


        }

    }
    private static boolean checked(int y,int x){
        return 0<=y && y<N && 0<=x && x<N;
    }

}
