package 레벨2.석유시추;

import java.util.*;

public class 석유시추 {
    static int [][] map;
    static boolean [][] isVisited;
    static int [] dy = {0,1,0,-1};
    static int [] dx = {1,0,-1,0};
    static Map<Integer,Integer> pointMap = new HashMap<>();
    public static void main(String[] args) {

    }
    public int solution(int[][] land) {
        int answer = 0;

        map = new int[land.length][land[0].length];
        isVisited = new boolean[land.length][land[0].length];
        for (int i = 0; i < map[0].length; i++) {
            pointMap.put(i,0);
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = land[i][j];
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(!isVisited[i][j] && map[i][j]==1) {
                    bfs(i,j);
                }
            }
        }

        for (int i = 0; i < map[0].length; i++) {
            answer = Math.max(pointMap.get(i),answer);
        }

        return answer;
    }

    private void bfs(int i, int j) {

        int minX = j;
        int maxX = j;
        int maxPoint = 0;

        Deque<int []> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        isVisited[i][j] = true;
        maxPoint++;

        while(!queue.isEmpty()) {
            int [] current =  queue.poll();

            for (int k = 0; k < 4; k++) {
                int nxtY = current[0] + dy[k];
                int nytX = current[1] + dx[k];

                if(0 > nxtY || nxtY >= map.length || 0 > nytX || nytX >= map[0].length) {
                    continue;
                }

                if(isVisited[nxtY][nytX]) {
                    continue;
                }

                if(map[nxtY][nytX] == 0) {
                    continue;
                }

                queue.add(new int[]{nxtY, nytX});
                isVisited[nxtY][nytX] = true;
                maxPoint++;

                if(nytX > maxX)
                    maxX = nytX;
                if(nytX < minX)
                    minX = nytX;
            }
        }

        for (int k = minX; k <= maxX; k++) {
            Integer point = pointMap.get(k);
            pointMap.put(k, point+maxPoint);
        }
    }
}
