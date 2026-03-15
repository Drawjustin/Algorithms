package 레벨2.방문길이;

public class main {
    class Solution {
        public int solution(String dirs) {
            int answer = 0;
            int [][][] map = new int [11][11][4];
            int [] now = new int [2];
            now[0] = 5;
            now[1] = 5;
            for (int i = 0; i < dirs.length(); i++) {
                char dir = dirs.charAt(i);
                move(map,dir,now);
            }

            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {
                    for (int k = 1; k <= 2; k++) {
                        if(map[i][j][k] == 1){
                            answer++;
                        }
                    }
                }
            }
            // U : 0, R : 1, D : 2, L : 3
            return answer;
        }
        public void move(int [][][] map, char dir, int [] now){

            switch(dir){
                case 'U' : {
                    int nxtY = now[0] + 1;
                    int nxtX = now[1];

                    if(nxtY < 0 || nxtY > 10 || nxtX < 0 || nxtX > 10)
                        break;

                    map[nxtY][nxtX][2] = 1;
                    now[0] = nxtY;
                    now[1] = nxtX;
                    break;
                }
                case 'R' : {
                    int nxtY = now[0];
                    int nxtX = now[1] + 1;

                    if(nxtY < 0 || nxtY > 10 || nxtX < 0 || nxtX > 10)
                        break;

                    map[now[0]][now[1]][1] = 1;
                    now[0] = nxtY;
                    now[1] = nxtX;

                    break;
                }
                case 'D' : {
                    int nxtY = now[0] - 1;
                    int nxtX = now[1];

                    if(nxtY < 0 || nxtY > 10 || nxtX < 0 || nxtX > 10)
                        break;

                    map[now[0]][now[1]][2] = 1;
                    now[0] = nxtY;
                    now[1] = nxtX;
                    break;
                }
                case 'L' : {
                    int nxtY = now[0];
                    int nxtX = now[1] - 1;

                    if(nxtY < 0 || nxtY > 10 || nxtX < 0 || nxtX > 10)
                        break;

                    map[nxtY][nxtX][1] = 1;
                    now[0] = nxtY;
                    now[1] = nxtX;
                    break;
                }
                default: break;
            }
        }
    }
}
