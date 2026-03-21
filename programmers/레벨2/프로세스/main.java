package 레벨2.프로세스;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class main {
    class Solution {
        public int solution(int[] priorities, int location) {
            int answer = 0;

            int [] sortedPriorities = Arrays.copyOf(priorities, priorities.length);
            Arrays.sort(sortedPriorities);
            int index = sortedPriorities.length - 1;
            Deque<int[]> queue = new ArrayDeque<>();


            for (int i = 0; i < priorities.length; i++) {
                queue.offer(new int[]{priorities[i], i});
            }

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                boolean hasHigher = false;

                if(cur[0] < sortedPriorities[index]){
                    hasHigher = true;
                }

                if (hasHigher) {
                    queue.offer(cur);
                } else {
                    answer++;
                    index--;
                    if (cur[1] == location) {
                        return answer;
                    }
                }
            }

            return answer;
        }
    }
}