package 완전범죄;

import java.util.Arrays;

public class 완전범죄dp2 {
    public int solution(int[][] info, int n, int m) {
        // dp[i][b] = i번째까지 처리, B 흔적이 b일 때 최소 A 흔적
        int[][] dp = new int[info.length + 1][m];

        // 초기화: 불가능한 값으로 설정
        for (int i = 0; i <= info.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0; // 시작: A 흔적 0, B 흔적 0

        // DP 전이
        for (int i = 0; i < info.length; i++) {
            for (int b = 0; b < m; b++) {
                if (dp[i][b] == Integer.MAX_VALUE) continue; // 도달 불가능

                int currentA = dp[i][b];

                // Case 1: A가 i번째 물건을 훔치기
                int newA = currentA + info[i][0];
                if (newA < n) {
                    dp[i + 1][b] = Math.min(dp[i + 1][b], newA);
                }

                // Case 2: B가 i번째 물건을 훔치기
                int newB = b + info[i][1];
                if (newB < m) {
                    dp[i + 1][newB] = Math.min(dp[i + 1][newB], currentA);
                }
            }
        }

        // 최종 답: 모든 가능한 B 흔적 중 최소 A 흔적 찾기
        int answer = Integer.MAX_VALUE;
        for (int b = 0; b < m; b++) {
            answer = Math.min(answer, dp[info.length][b]);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}

