package 레벨2.주차요금계산;

import java.util.*;

public class main {
    class Solution {
        public int[] solution(int[] fees, String[] records) {
            int[] answer = {};

            int defaultTime = fees[0];
            int defaultCost = fees[1];
            int unitTime = fees[2];
            int unitCost = fees[3];

            Map<String, Integer> In = new HashMap<>();
            Map<String, Integer> Out = new HashMap<>();

            for (int i = 0; i < records.length; i++) {
                String [] token = records[i].split(" ");

                String [] times = token[0].split(":");
                int minute = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
                String number = token[1];
                String order = token[2];

                if(order.equals("IN")){
                    In.put(number, minute);
                }else{
                    Out.put(number, Out.getOrDefault(number, 0) + minute - In.get(number));
                    In.remove(number);
                }
            }

            int maxMinute = 23 * 60 + 59;
            for (String s : In.keySet()) {
                Out.put(s, Out.getOrDefault(s, 0) + maxMinute - In.get(s));
            }

            // [0] 차번호 [1] 요금
            List<int []> list = new ArrayList<>();

            for (String number : Out.keySet()) {
                int totalTime = Out.get(number);
                int cost = defaultCost; // 기본 요금 세팅

                // 🎯 2. 요금 계산 공식 수정 (기본 시간 초과 시에만 추가 요금, 올림 처리)
                if (totalTime > defaultTime) {
                    cost += (int) Math.ceil((double) (totalTime - defaultTime) / unitTime) * unitCost;
                }

                list.add(new int[]{Integer.parseInt(number), cost});

            }
            Collections.sort(list, (o1, o2) -> o1[0] - o2[0]);

            answer = list.stream().mapToInt(i -> i[1]).toArray();
            return answer;
        }
    }
}
