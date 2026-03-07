package 레벨2.행렬테두리회전하기;

import java.util.Arrays;
import java.util.PriorityQueue;

public class main {
    class Solution {
        public int[] solution(int rows, int columns, int[][] queries) {
            int[] answer = new int[queries.length];
            int[][] map = new int[rows + 1][columns + 1];

            // 1. 초기 맵 설정 (수정된 공식 적용)
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= columns; j++) {
                    map[i][j] = ((i - 1) * columns) + j;
                }
            }

            // 2. 쿼리별로 회전 수행
            for (int i = 0; i < queries.length; i++) {
                int r1 = queries[i][0];
                int c1 = queries[i][1];
                int r2 = queries[i][2];
                int c2 = queries[i][3];

                // 시작점의 값을 임시 저장 (이 값이 시작 최솟값이 됨)
                int temp = map[r1][c1];
                int min = temp;

                // ① 좌측 테두리: 아래에서 위로 당기기
                for (int r = r1; r < r2; r++) {
                    map[r][c1] = map[r + 1][c1];
                    min = Math.min(min, map[r][c1]); // 최솟값 갱신
                }

                // ② 하단 테두리: 오른쪽에서 왼쪽으로 당기기
                for (int c = c1; c < c2; c++) {
                    map[r2][c] = map[r2][c + 1];
                    min = Math.min(min, map[r2][c]);
                }

                // ③ 우측 테두리: 위에서 아래로 당기기
                for (int r = r2; r > r1; r--) {
                    map[r][c2] = map[r - 1][c2];
                    min = Math.min(min, map[r][c2]);
                }

                // ④ 상단 테두리: 왼쪽에서 오른쪽으로 당기기
                for (int c = c2; c > c1 + 1; c--) {
                    map[r1][c] = map[r1][c - 1];
                    min = Math.min(min, map[r1][c]);
                }

                // ⑤ 임시 저장해둔 값을 원래 위치의 바로 오른쪽 칸에 삽입
                map[r1][c1 + 1] = temp;

                // 결과 배열에 최솟값 저장
                answer[i] = min;
            }

            return answer;
        }
    }
}
