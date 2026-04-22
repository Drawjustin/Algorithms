package 레벨2.선인장숨기기;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main2 {
    public class Solution {
        static int [][] rowMin;
        static int [][] original;
        public int[] solution(int m, int n, int h, int w, int[][] drops) {
            rowMin = new int[m][n - w + 1];
            original = new int [m][n];

            for (int i = 0; i < m; i++) {
                Arrays.fill(original[i], Integer.MAX_VALUE);
            }

            for (int i = 0; i < drops.length; i++) {
                original[drops[i][0]][drops[i][1]] =
                        Math.min(original[drops[i][0]][drops[i][1]], i + 1);
            }

            for (int i = 0; i < m; i++) {
                Deque<Integer> deque = new ArrayDeque<>();
                for (int j = 0; j < n; j++) {
                    int value = original[i][j];

                    while(!deque.isEmpty() && original[i][deque.peekLast()] >= value)
                        deque.pollLast();
                    deque.offerLast(j);

                    while(!deque.isEmpty() && deque.peekFirst() <= j - w)
                        deque.pollFirst();

                    if(j - w  + 1 >= 0){
                        rowMin[i][j - w + 1] = original[i][deque.peekFirst()];
                    }
                }
            }
            int bestValue = -1;
            int bestY = Integer.MAX_VALUE;
            int bestX = Integer.MAX_VALUE;

            for (int i = 0; i <= n - w; i++) {
                Deque<Integer> deque = new ArrayDeque<>();
                for (int j = 0; j < m; j++) {
                    int value = rowMin[j][i];

                    while(!deque.isEmpty() && rowMin[deque.peekLast()][i] >= value)
                        deque.pollLast();
                    deque.offerLast(j);
                    while(!deque.isEmpty() && j - h  >= deque.peekFirst())
                        deque.pollFirst();


                    if(j - h + 1 >= 0){
                        if(bestValue < rowMin[deque.peekFirst()][i]){
                            bestValue = rowMin[deque.peekFirst()][i];
                            bestY = j - h + 1;
                            bestX = i;
                        }else if(bestValue == rowMin[deque.peekFirst()][i]){
                            if(bestY > j - h + 1 || (bestY == j - h + 1 && bestX > i)){
                                bestY = j - h + 1;
                                bestX = i;
                            }
                        }
                    }

                }
            }
            return new int []{bestY,bestX};

        }
    }
}
