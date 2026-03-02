package 레벨2.할인행사;

import java.util.HashMap;
import java.util.Map;

public class main {
    class Solution {
        public int solution(String[] want, int[] number, String[] discount) {
            int answer = 0;

            // 1. 정답지 Map 만들기 (내가 원하는 과일과 개수)
            Map<String, Integer> wantMap = new HashMap<>();
            for (int i = 0; i < want.length; i++) {
                wantMap.put(want[i], number[i]);
            }

            // 2. 현재 10일치 장바구니 Map 만들기
            Map<String, Integer> discountMap = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
            }

            // ✨ 마법의 한 줄: 두 Map이 완벽히 똑같은지 비교!
            if (wantMap.equals(discountMap)) answer++;

            // 3. 슬라이딩 윈도우
            for (int i = 10; i < discount.length; i++) {
                // [왼쪽] 빠지는 항목 처리
                String leftItem = discount[i - 10];
                discountMap.put(leftItem, discountMap.get(leftItem) - 1);
                // 개수가 0이 되면 Map에서 아예 삭제해줘야 equals() 비교가 제대로 됨!
                if (discountMap.get(leftItem) == 0) {
                    discountMap.remove(leftItem);
                }

                // [오른쪽] 들어오는 항목 처리
                String rightItem = discount[i];
                discountMap.put(rightItem, discountMap.getOrDefault(rightItem, 0) + 1);

                // ✨ 이동할 때마다 통째로 비교!
                if (wantMap.equals(discountMap)) answer++;
            }

            return answer;
        }
    }
}
