package 레벨2.완전범죄;

import java.util.*;

public class 완전범죄dfs {
    public int solution(int[][] info, int n, int m) {
        // 상태: [물건 인덱스, A 흔적, B 흔적]
        Queue<int[]> queue = new LinkedList<>();
        // visited[i][a][b] = i번째까지 처리, A=a, B=b 방문 여부
        boolean[][][] visited = new boolean[info.length + 1][n][m];

        queue.offer(new int[]{0, 0, 0});
        visited[0][0][0] = true;

        int minA = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int idx = current[0];
            int sumA = current[1];
            int sumB = current[2];

            // 모든 물건 처리 완료
            if (idx == info.length) {
                minA = Math.min(minA, sumA);
                continue;
            }

            // Case 1: A가 훔치기
            int newA = sumA + info[idx][0];
            if (newA < n && !visited[idx + 1][newA][sumB]) {
                visited[idx + 1][newA][sumB] = true;
                queue.offer(new int[]{idx + 1, newA, sumB});
            }

            // Case 2: B가 훔치기
            int newB = sumB + info[idx][1];
            if (newB < m && !visited[idx + 1][sumA][newB]) {
                visited[idx + 1][sumA][newB] = true;
                queue.offer(new int[]{idx + 1, sumA, newB});
            }
        }

        return minA == Integer.MAX_VALUE ? -1 : minA;
    }
}