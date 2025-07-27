import java.util.*;
import java.io.*;
class Solution {
    public static int solution(String[] storage, String[] requests) {
        int answer = 0;
        char [][] map = new char [storage.length+2][storage[0].length()+2];

        for (int i = 1; i <= storage.length; i++) {
            for (int j = 1; j <= storage[0].length(); j++) {
                map[i][j] = storage[i-1].charAt(j-1);
            }
        }

        for (String request : requests) {
            if (request.length() == 2) {
                delete(map, request.charAt(0));
            } else {
                bfs(map,request.charAt(0));
            }
        }
        for (int i = 1; i <= storage.length; i++) {
            for (int j = 1; j <= storage[0].length(); j++) {
                if(map[i][j] == '-')
                    continue;
                answer++;
            }
        }

        return answer;
    }
    private static void delete(char[][] map, char c) {
        for (int i = 1; i < map.length-1; i++) {
            for (int j = 1; j < map[0].length-1; j++) {
                if(map[i][j] == c)
                    map[i][j] = '-';
            }
        }
    }
    private static void bfs(char[][] map, char c) {
        boolean [][] isVisited = new boolean[map.length][map[0].length];
        int [] dy = new int []{-1,0,1,0};
        int [] dx = new int []{0,1,0,-1};
        Deque<int []> deque = new ArrayDeque<>();
        deque.add(new int[]{0,0});
        isVisited[0][0] = true;
        while(!deque.isEmpty()){
            int [] temp =  deque.poll();
            for (int i = 0; i < 4; i++) {
                int nxtY = temp[0] + dy[i];
                int nxtX = temp[1] + dx[i];
                if(!isMoved(map,nxtY,nxtX) || isVisited[nxtY][nxtX])
                    continue;

                isVisited[nxtY][nxtX] = true;

                if(map[nxtY][nxtX] == c){
                    map[nxtY][nxtX] = '!';
                }


                if(map[nxtY][nxtX] == '\0' || map[nxtY][nxtX] == '-') {
                    deque.add(new int[]{nxtY, nxtX});
                }
            }
        }
        for(int i=1; i < map.length-1; i++){
            for(int j=1; j < map[0].length-1; j++){
                if(map[i][j] == '!')
                    map[i][j] = '-';
            }
        }
    }
    private static boolean isMoved (char[][] map, int y,int x){
        return 0 <= y && y < map.length && 0 <= x && x < map[0].length;
    }
}