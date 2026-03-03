package 레벨2.피로도;

public class main {
    class Solution {
        int result = 0;
        public int solution(int k, int[][] dungeons) {
            boolean [] isVisited = new  boolean[dungeons.length];
            int answer = -1;
            dfs(dungeons,k,0,0, isVisited);
            answer = result;
            return answer;
        }

        public void dfs(int [][] dungeons, int curK, int curDungeons, int clear, boolean [] isVisited){
                result = Math.max(result, clear);
            for (int i = 0; i < dungeons.length; i++) {
                if(!isVisited[i] && curK >= dungeons[i][0]){
                    isVisited[i] = true;
                    dfs(dungeons, curK - dungeons[i][1], curDungeons + 1, clear + 1,isVisited);
                    isVisited[i] = false;
                }


            }
        }
    }
}
