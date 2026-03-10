package 레벨2.삼각달팽이;

public class main {
    class Solution {
        public int[] solution(int n) {
            int[][] arr = new int[n][n];
            int num = 1;

            int x = 0;
            int y = -1;

            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    if (i % 3 == 0) {        // 아래
                        y++;
                    } else if (i % 3 == 1) { // 오른쪽
                        x++;
                    } else {                 // 왼쪽 위
                        x--;
                        y--;
                    }

                    arr[y][x] = num++;
                }
            }

            int[] answer = new int[n * (n + 1) / 2];
            int idx = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    answer[idx++] = arr[i][j];
                }
            }

            return answer;
        }
    }
}
