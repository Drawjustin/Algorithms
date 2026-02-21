package 레벨2.리코쳇로봇;

import java.util.ArrayDeque;
import java.util.Deque;

public class main {
    class Solution {
        static int [] dy = {0,1,0,-1};
        static int [] dx = {1,0,-1,0};
        public int solution(String[] board) {
            int [][] map = new int [board.length][board[0].length()];
            int [] start = new int [2];
            int [] end = new int [2];
            boolean [][] isVisited = new boolean[board.length][board[0].length()];
            int answer = -1;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length(); j++) {
                    switch (board[i].charAt(j)){
                        case '.': map[i][j] = 0; // 길
                            break;
                        case 'D': map[i][j] = 1; // 벽
                            break;
                        case 'R': { start[0] = i; start[1] = j; map[i][j] = 2;} // 시작지점
                            break;
                        case 'G': {end[0] = i; end[1] = j; map[i][j] = 3;} // 도착지점
                            break;
                    }
                }
            }

            Deque<int []> queue = new ArrayDeque<>();
            queue.add(new int [] {start[0], start[1], 0});
            isVisited[start[0]][start[1]] = true;

            while (!queue.isEmpty()) {
                int [] now =  queue.poll();

                if(map[now[0]][now[1]] == 3) {
                    answer = now[2];
                    break;
                }


                for (int i = 0; i < 4; i++) {
                    int nxtY = now[0];
                    int nxtX = now[1];
                    while(true){
                        nxtY += dy[i];
                        nxtX += dx[i];
                        if(isOutMap(nxtY,nxtX,board.length,board[0].length())){
                            nxtY -= dy[i];
                            nxtX -= dx[i];
                            break;
                        }
                        if(map[nxtY][nxtX] == 1) {
                            nxtY -= dy[i];
                            nxtX -= dx[i];
                            break;
                        }
                    }
                    if(isVisited[nxtY][nxtX])
                        continue;
                    isVisited[nxtY][nxtX] = true;
                    queue.add(new int [] {nxtY, nxtX, now[2] + 1});
                }
            }

            return answer;
        }

        boolean isOutMap(int y, int x, int limitY, int limitX) {
            return 0 > y || y >= limitY || 0 > x || x >= limitX;
        }
    }
}