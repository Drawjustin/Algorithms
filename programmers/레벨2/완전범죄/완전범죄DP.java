package 레벨2.완전범죄;

public class 완전범죄DP {
    public static void main(String[] args){
        /**
         * 10:40 시작
         */
        
    }

    public int solution(int[][] info, int n, int m) {
        // dp[i][a][b] = i번째 물건까지 처리했을 때,
        //               A 흔적 = a, B 흔적 = b 상태가 도달 가능한가?
        boolean[][][] dp = new boolean[info.length + 1][n][m];

        // 초기값: 아무것도 훔치지 않은 상태 (흔적 0, 0)
        dp[0][0][0] = true;

        // 점화식: 각 물건을 순서대로 처리
        for (int i = 0; i < info.length; i++) {
            for (int a = 0; a < n; a++) {
                for (int b = 0; b < m; b++) {
                    if (!dp[i][a][b]) continue; // 도달 불가능한 상태는 스킵

                    // Case 1: A가 i번째 물건을 훔치는 경우
                    int newA = a + info[i][0];
                    if (newA < n) {
                        dp[i+1][newA][b] = true;
                    }

                    // Case 2: B가 i번째 물건을 훔치는 경우
                    int newB = b + info[i][1];
                    if (newB < m) {
                        dp[i+1][a][newB] = true;
                    }
                }
            }
        }

        // 최종 답: 모든 물건을 처리한 후, 가능한 최소 A 흔적 찾기
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                if (dp[info.length][a][b]) {
                    return a; // 가장 작은 a 반환
                }
            }
        }

        return -1; // 불가능한 경우
    }
}