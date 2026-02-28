package 레벨2.귤고르기;

import java.util.*;

public class main {
    class Solution{
        public int solution(int k, int[] tangerine) {
            int answer = 0;

            Map<Integer, Integer> map = new HashMap<>();
            for (int i : tangerine) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
            int[] counts = new int[map.size()];
            int idx = 0;
            for (int c : map.values()) {   // keySet+get 대신 values()
                counts[idx++] = c;
            }

            Arrays.sort(counts); // 오름차순

            int sum = 0;
            for (int i = counts.length - 1; i >= 0 && sum < k; i--) {
                sum += counts[i];
                answer++;
            }

            return answer;
        }
    }
}
