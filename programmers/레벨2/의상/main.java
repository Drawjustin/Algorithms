package 레벨2.의상;

import java.util.HashMap;
import java.util.Map;

public class main {
    class Solution {
        public int solution(String[][] clothes) {
            Map<String, Integer> map = new HashMap<>();

            for (String[] cloth : clothes) {
                map.put(cloth[1], map.getOrDefault(cloth[1], 0) + 1);
            }

            int answer = 1;
            for (int count : map.values()) {
                answer *= (count + 1);
            }

            return answer - 1;
        }
    }
}
