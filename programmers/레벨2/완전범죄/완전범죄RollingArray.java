package 레벨2.완전범죄;

import java.util.Arrays;

public class 완전범죄RollingArray {
    public int solution(int[][] info, int n, int m) {
        // 현재와 다음 단계만 저장 (공간 O(m))
        int[] dp = new int[m];
        int[] next = new int[m];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // 초기: A=0, B=0

        for (int i = 0; i < info.length; i++) {
            Arrays.fill(next, Integer.MAX_VALUE);

            for (int b = 0; b < m; b++) {
                if (dp[b] == Integer.MAX_VALUE) continue;

                int currentA = dp[b];

                // A가 훔치기
                int newA = currentA + info[i][0];
                if (newA < n) {
                    next[b] = Math.min(next[b], newA);
                }

                // B가 훔치기
                int newB = b + info[i][1];
                if (newB < m) {
                    next[newB] = Math.min(next[newB], currentA);
                }
            }

            // swap
            int[] temp = dp;
            dp = next;
            next = temp;
        }

        int answer = Integer.MAX_VALUE;
        for (int b = 0; b < m; b++) {
            answer = Math.min(answer, dp[b]);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
