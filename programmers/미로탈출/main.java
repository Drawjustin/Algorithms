package 미로탈출;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class main {
    class Solution{
        public int solution(String[] maps){
            int answer = -1;

            int [] dx = {1,0,-1,0};
            int [] dy = {0,1,0,-1};

            int y = maps.length;
            int x = maps[0].length();

            char[][] newMaps =  new char[y][x];
            boolean[][][] isVisited = new boolean[y][x][2];
            int [] start = new int [4];
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    newMaps[i][j] = maps[i].charAt(j);
                    if(newMaps[i][j] == 'S') {
                        start[0] = i;
                        start[1] = j;
                    }
                }
            }


            Deque<int[]> queue = new LinkedList<>();
            queue.add(start);
            isVisited[start[0]][start[1]][start[2]] = true;

            while(!queue.isEmpty()) {
                int [] node = queue.poll();

                if(node[2] == 1 && newMaps[node[0]][node[1]] == 'E') {
                    answer = node[3];
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nxtY = node[0] + dy[i];
                    int nxtX = node[1] + dx[i];

                    if(0 > nxtY || nxtY >= y || 0 > nxtX || nxtX >= x)
                        continue;

                    if(newMaps[nxtY][nxtX] == 'X')
                        continue;

                    if(isVisited[nxtY][nxtX][node[2]])
                        continue;

                    if(newMaps[nxtY][nxtX] == 'L'){
                        queue.add(new int []{nxtY,nxtX,1,node[3]+1});
                        isVisited[nxtY][nxtX][1] = true;
                        continue;
                    }

                    queue.add(new int []{nxtY,nxtX,node[2],node[3]+1});
                    isVisited[nxtY][nxtX][node[2]] = true;

                }
            }
            return answer;
        }
    }
}
