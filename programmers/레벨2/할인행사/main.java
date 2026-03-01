package 레벨2.할인행사;

import java.util.HashMap;
import java.util.Map;

public class main {

    class Solution {
        public int solution(String[] want, int[] number, String[] discount) {
            int answer = 0;
            int[] curWants = new int[number.length];
            Map<String, Integer> map = new HashMap<>();

            // 1. 과일 이름에 인덱스 번호 부여
            for (int i = 0; i < want.length; i++) {
                map.put(want[i], i);
            }

            // 2. 초기 10일 세팅
            for (int i = 0; i < 10; i++) {
                int index = map.getOrDefault(discount[i], -1);
                if(index != -1) curWants[index]++;
            }

            // 첫 10일 검사
            if(isMatch(curWants, number)) answer++;

            // 3. 슬라이딩 윈도우 (한 칸씩 밀기)
            for (int i = 10; i < discount.length; i++) {
                int leftIndex = map.getOrDefault(discount[i - 10], -1);
                if(leftIndex != -1) curWants[leftIndex]--;

                int rightIndex = map.getOrDefault(discount[i], -1);
                if(rightIndex != -1) curWants[rightIndex]++;

                // 윈도우 이동 후 검사
                if(isMatch(curWants, number)) answer++;
            }

            return answer;
        }

        // 🟢 일일이 for문 안에서 count 세던 걸 이렇게 분리하면 훨씬 우아해짐!
        private boolean isMatch(int[] curWants, int[] number) {
            for (int i = 0; i < number.length; i++) {
                if (curWants[i] != number[i]) {
                    return false; // 하나라도 틀리면 바로 탈락!
                }
            }
            return true; // 무사히 다 통과하면 정답!
        }
    }
}
