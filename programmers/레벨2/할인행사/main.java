package 레벨2.할인행사;

import java.util.HashMap;
import java.util.Map;

public class main {
    class Solution {
        public int solution(String[] want, int[] number, String[] discount) {
            int answer = 0;

            int [] curWants = new int[number.length];

            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < want.length; i++) {
                map.put(want[i],0);
            }

            for (int i = 0; i < 10; i++) {
                int index = map.getOrDefault(discount[i], -1);
                if(index == -1)
                    continue;
                curWants[index]++;
            }


            for (int i = 0; i < number.length; i++) {
                int count = 0;
                if(number[i] == curWants[i]){
                    count++;
                }
                if(count == number.length){
                    answer++;
                }
            }

            for (int i = 10; i <discount.length ; i++) {
                int innerIndex = map.getOrDefault(discount[i - 10], -1);
                if(innerIndex == -1)
                    continue;
                curWants[innerIndex]--;

                int nxtIndex = map.getOrDefault(discount[i], -1);
                if(nxtIndex == -1)
                    continue;
                curWants[nxtIndex]++;

                for (int j = 0; j < number.length; j++) {
                    int count = 0;
                    if(number[j] == curWants[j]){
                        count++;
                    }
                    if(count == number.length){
                        answer++;
                    }
                }
            }

            return answer;
        }
    }
}
