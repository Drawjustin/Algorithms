package 레벨2.선인장숨기기;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class main {
    class Solution {
        public int[] solution(int m, int n, int h, int w, int[][] drops) {
            int [][] map = new int [m][n];

            for (int i = 0; i < m; i++) {
                Arrays.fill(map[i], Integer.MAX_VALUE);
            }

            for(int i = 0; i < drops.length; i++){
                map[drops[i][0]][drops[i][1]] = i + 1;
            }



            int [][] rowMin = new int[m][n- w +1];

            for (int i = 0; i < m; i++) {
                Deque<Integer> queue = new ArrayDeque<>();
                for (int j = 0; j < n; j++) {
                    int value = map[i][j];
                    while(!queue.isEmpty() && map[i][queue.peekLast()] >= value)
                        queue.pollLast();
                    queue.offerLast(j);

                    while(!queue.isEmpty() && queue.peekFirst() <= j - w)
                        queue.pollFirst();

                    if(j >= w - 1){
                        rowMin[i][j - w + 1] = map[i][queue.peekFirst()];
                    }
                }
            }

            int bestValue = -1;
            int bestY = Integer.MAX_VALUE;
            int bestX = Integer.MAX_VALUE;

            for (int i = 0; i < n - w + 1; i++) {
                Deque<Integer> queue = new ArrayDeque<>();
                for (int j = 0; j < m; j++) {
                    int value = rowMin[j][i];

                    while(!queue.isEmpty() && rowMin[queue.peekLast()][i] >= value)
                        queue.pollLast();

                    queue.offerLast(j);

                    while(!queue.isEmpty() && queue.peekFirst() <= j - h)
                        queue.pollFirst();

                    if(j >= h - 1){
                        int V = rowMin[queue.peekFirst()][i];
                        int startY = j - h + 1;
                        if(bestValue < V){
                            bestValue = V;
                            bestY = startY;
                            bestX = i;
                        }else if(bestValue == V){
                            if(bestY > startY || (bestY == startY && bestX > i)){
                                bestY = startY;
                                bestX = i;
                            }
                        }
                    }
                }
            }
            return new int[]{bestY,bestX};
        }
    }
}
