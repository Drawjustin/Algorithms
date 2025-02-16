package Baekjoon_11967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class VERTEX{
        int y,x;
        VERTEX(int y, int x){
            this.y = y;
            this.x = x;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VERTEX vertex = (VERTEX) o;
            return y == vertex.y && x == vertex.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }
    static int N,M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int [][][] map;
    static boolean [][] isvisited;
    static Map<VERTEX,ArrayList<VERTEX>> hashMap = new HashMap<>();
    static int [] dx = {1,0,-1,0};
    static int [] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        map = new int[N+1][N+1][2];
        isvisited = new boolean[N+1][N+1];


        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            VERTEX vertex1 = new VERTEX(x,y);
            VERTEX vertex2 = new VERTEX(a,b);
            if(!hashMap.containsKey(vertex1)){
                ArrayList<VERTEX> vertices = new ArrayList<>();
                vertices.add(vertex2);
                hashMap.put(vertex1,vertices);
            }else{
                ArrayList<VERTEX> vertices = hashMap.get(vertex1);
                vertices.add(vertex2);
            }
        }

        bfs();


        int result = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(map[i][j][1] == 1)
                    result++;
            }
        }

        System.out.println(result);

    }

    private static void bfs() {
        Deque<VERTEX> deque = new LinkedList<>();
        isvisited[1][1] = true;
        deque.add(new VERTEX(1,1));
        map[1][1][0] = 1;
        map[1][1][1] = 1;

        while(!deque.isEmpty()){
            VERTEX temp = deque.poll();

            if(hashMap.containsKey(temp)){
                ArrayList<VERTEX> vertices = hashMap.get(temp);
                for (VERTEX vertex : vertices) {
                    map[vertex.y][vertex.x][0] = 1;
                    map[vertex.y][vertex.x][1] = 1;
                }
                hashMap.remove(temp);
                for (int i = 1; i <= N; i++) {
                    Arrays.fill(isvisited[i],false);
                }
            }

            for (int i = 0; i < 4; i++) {

                if(1 > temp.y + dy[i] ||  temp.y + dy[i] > N || 1 > temp.x + dx[i] || temp.x + dx[i] > N)
                    continue;

                if(isvisited[temp.y + dy[i]][temp.x + dx[i]])
                    continue;

                if(map[temp.y+dy[i]][temp.x+dx[i]][0] == 0)
                    continue;

                map[temp.y+dy[i]][temp.x+dx[i]][1] = 1;
                isvisited[temp.y+dy[i]][temp.x+dx[i]] = true;
                deque.add(new VERTEX(temp.y+dy[i],temp.x+dx[i]));
            }




        }
    }

}
