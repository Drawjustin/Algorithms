package 레벨2.기능개발;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class main {
    class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            int[] answer = {};

            Deque<int []> jobs = new ArrayDeque<>();

            for (int i = 0; i < progresses.length; i++) {
                jobs.offer(new int []{progresses[i], speeds[i]});
            }
            List<Integer> result = new ArrayList<>();

            while(!jobs.isEmpty()){
                doJob(jobs);
                int doneTask = doResult(jobs);
                if (doneTask > 0) {
                    result.add(doneTask);
                }
            }
            answer = result.stream().mapToInt(Integer::intValue).toArray();
            return answer;
        }

        private int doResult(Deque<int[]> jobs) {
            int doneTask = 0;
            while(!jobs.isEmpty() && jobs.peek()[0] >= 100){
                jobs.poll();
                doneTask++;
            }
            return doneTask;
        }

        private void doJob(Deque<int []> jobs) {
            for (int i = 0; i < jobs.size(); i++) {
                int [] curNode = jobs.poll();
                curNode[0] += curNode[1];
                jobs.offer(curNode);
            }
        }
    }
}
