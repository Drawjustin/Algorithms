package 레벨2.더맵게;

import java.util.PriorityQueue;

public class main {
    class Solution {
        public int solution(int[] scoville, int K) {
            PriorityQueue<Long> pq = new PriorityQueue<>();

            int mix = 0;
            for (int i = 0; i < scoville.length; i++) {
                pq.add((long) scoville[i]);
            }


            while(pq.size() >= 2 && pq.peek() < K) {
                Long cur =  pq.poll();
                Long cur2 =  pq.poll();
                mix++;
                cur = cur + cur2 * 2L;
                pq.add(cur);
            }


            return pq.peek() < K ? -1 : mix;
        }
    }
}
