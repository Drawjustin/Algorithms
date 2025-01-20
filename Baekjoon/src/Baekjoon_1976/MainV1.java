package Baekjoon_1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MainV1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M;

    /**
     * 동혁이는 친구들과 함께 여행을 가려고 한다. 한국에는 도시가 N개 있고 임의의 두 도시 사이에 길이 있을 수도, 없을 수도 있다.
     * 동혁이의 여행 일정이 주어졌을 때, 이 여행 경로가 가능한 것인지 알아보자. 물론 중간에 다른 도시를 경유해서 여행을 할 수도 있다.
     * 예를 들어 도시가 5개 있고, A-B, B-C, A-D, B-D, E-A의 길이 있고, 동혁이의 여행 계획이 E C B C D 라면 E-A-B-C-B-C-B-D라는 여행경로를 통해 목적을 달성할 수 있다.
     */
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int [][] map = new int [N][N];
        boolean [] canVisited = new boolean[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        StringTokenizer stk = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(stk.nextToken())-1;
        bfs(start, map, canVisited);

        while(stk.hasMoreTokens()){
            int nxt = Integer.parseInt(stk.nextToken())-1;

            if(!canVisited[nxt]){
                System.out.println("NO");
                return;
            }

            bfs(nxt,map,canVisited);


        }
        System.out.println("YES");

    }

    private static void bfs(int start,int [][] map, boolean [] visited) {
        Deque<Integer> deque = new ArrayDeque<>();
        visited[start] = true;
        deque.add(start);
        while(!deque.isEmpty()){
            int temp = deque.poll();
            for (int i = 0; i < N; i++) {
                if(!visited[i] && map[temp][i] == 1){
                    deque.add(i);
                    visited[i] = true;
                }
            }

        }
    }
}
