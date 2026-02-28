package 레벨2.귤고르기;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class main {
    class Solution{
        public int solution(int k, int[] tangerine) {
            int answer = 0;

            Map<Integer, Integer> map = new HashMap<>();
            for (int i : tangerine) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            int count = 0;
            for(int i : map.keySet()){
                pq.add(map.get(i));
            }
            while (count < k && !pq.isEmpty()){
                count += pq.poll();
                answer++;
            }

            return answer;
        }
    }
}
