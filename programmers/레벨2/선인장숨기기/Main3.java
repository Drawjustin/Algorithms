package 레벨2.선인장숨기기;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main3 {
    class Solution {
        public int[] solution(int m, int n, int h, int w, int[][] drops) {
            int[] answer = {};

            int [][] originalMap = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(originalMap[i], Integer.MAX_VALUE);
            }

            for (int i = 0; i < drops.length; i++) {
                originalMap[drops[i][0]][drops[i][1]] = i + 1;
            }

            int [][] rowMap = new int[m][n - w + 1];

            for (int i = 0; i < m; i++) {
                Deque<Integer> deque = new ArrayDeque<>();
                for (int j = 0; j < n; j++) {

                    while(!deque.isEmpty() && originalMap[i][deque.peekLast()] >= originalMap[i][j])
                        deque.pollLast();
                    deque.offer(j);
                    while(!deque.isEmpty() && deque.peekFirst() <= j - w)
                        deque.pollFirst();

                    if(j - w + 1 >= 0){
                        rowMap[i][j - w + 1] = originalMap[i][deque.peekFirst()];
                    }
                }
            }

            int resultY = Integer.MAX_VALUE;
            int resultX = Integer.MAX_VALUE;
            int time = -1;
            for (int i = 0; i < n - w + 1; i++) {
                Deque<Integer> deque = new ArrayDeque<>();
                for (int j = 0; j < m; j++) {
                    while(!deque.isEmpty() && rowMap[deque.peekLast()][i] >= rowMap[j][i])
                        deque.pollLast();
                    deque.offer(j);
                    while(!deque.isEmpty() && deque.peekFirst() <= j - h)
                        deque.pollFirst();

                    if(j - h + 1 >= 0){
                        if(rowMap[deque.peekFirst()][i] == time){
                            if(resultY > j - h + 1 || (resultY == (j - h + 1) && resultX > i)){
                                resultY = j - h + 1;
                                resultX = i;
                            }
                        }else if(rowMap[deque.peekFirst()][i] > time){
                            resultY = j - h + 1;
                            resultX = i;
                            time = rowMap[deque.peekFirst()][i];
                        }
                    }
                }
            }

            answer = new int []{resultY,resultX};

            return answer;
        }
    }
}
