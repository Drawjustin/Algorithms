package 레벨2.시소짝꿍;

import java.util.*;

public class main {
    class Solution {
        public long solution(int[] weights) {
            long answer = 0;

            // 빈도수를 저장할 Map (오버플로우 방지를 위해 Long 타입 사용)
            Map<Integer, Long> map = new HashMap<>();

            for (int weight : weights) {
                map.put(weight, map.getOrDefault(weight, 0L) + 1L);
            }

            // 중복 없이 고유한 몸무게들만 순회
            for (int w : map.keySet()) {
                long count = map.get(w);

                // 1. 같은 몸무게끼리 짝꿍이 되는 경우 (nC2)
                if (count > 1) {
                    answer += (count * (count - 1)) / 2;
                }

                // 2. 다른 몸무게와 짝꿍이 되는 경우
                // 중복 카운트를 막기 위해 기준 무게(w)보다 '무거운' 짝꿍만 찾습니다.

                // 2:3 비율
                if (w % 2 == 0 && map.containsKey(w * 3 / 2)) {
                    answer += count * map.get(w * 3 / 2);
                }
                // 1:2 비율
                if (map.containsKey(w * 2)) {
                    answer += count * map.get(w * 2);
                }
                // 3:4 비율
                if (w % 3 == 0 && map.containsKey(w * 4 / 3)) {
                    answer += count * map.get(w * 4 / 3);
                }
            }

            return answer;
        }
    }
//    class Solution {
//        int [] siso = new int[4001];
//        int [] origin = new int[1001];
//        public long solution(int[] weights) {
//            long answer = 0;
//            Arrays.sort(weights);
//
//            for(int i=0; i<weights.length; i++){
//                int temp = origin[weights[i]];
//                if(temp >0){
//                    answer += temp;
//                    answer += siso[weights[i]*2] - origin[weights[i]];
//                    answer += siso[weights[i]*3] - origin[weights[i]];
//                    answer += siso[weights[i]*4] - origin[weights[i]];
//                }else{
//                    for(int j=2; j<=4; j++){
//                        answer += siso[weights[i]*j];
//                    }
//                }
//                origin[weights[i]]++;
//                siso[weights[i]*2]++;
//                siso[weights[i]*3]++;
//                siso[weights[i]*4]++;
//            }
//
//
//            return answer;
//        }
//    }
}
