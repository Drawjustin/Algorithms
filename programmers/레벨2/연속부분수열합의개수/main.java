package 레벨2.연속부분수열합의개수;

import java.util.HashSet;
import java.util.Set;

public class main {
    class Solution {
        public int solution(int[] elements) {
            Set<Integer> set = new HashSet<>();
            int n = elements.length;

            // i는 부분 수열이 시작할 인덱스 (0부터 끝까지)
            for (int i = 0; i < n; i++) {
                int sum = 0;

                // j는 부분 수열의 길이 (1개부터 n개까지 덧붙임)
                for (int j = 0; j < n; j++) {
                    // 핵심: (시작점 + 더해갈 개수) % 배열길이
                    // 인덱스가 배열 끝을 넘어가도 % n 덕분에 다시 0으로 돌아옴!
                    sum += elements[(i + j) % n];
                    set.add(sum);
                }
            }

            return set.size();
        }
    }
}
