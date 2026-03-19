package 레벨2.카펫;

public class main {
    class Solution {
        int[] answer = new int[2];
        public int[] solution(int brown, int yellow) {

            int curX = 3;
            int curY = 3;
            int curBrown = 8;
            int curYellow = 1;

            dfs(curY,curX,curBrown,curYellow,brown,yellow);

            return answer;
        }
        public void dfs(int y, int x , int cB, int cY, int B, int Y) {
            if(answer[0] != 0){
                return;
            }
            if(cB==B && cY==Y){
                answer = new int []{y,x};
                return;
            }

            if(cB >= B || cY >= Y){
                return;
            }
            dfs(y+1,x,cB+2, cY+x-2,B,Y);
            dfs(y,x+1,cB+2,cY+y-2,B,Y);
        }
    }
}
