package 레벨2.선인장숨기기;

import java.util.Arrays;

public class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[][] map = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }

        int k = 1;
        for (int[] drop : drops) {
            int y = drop[0];
            int x = drop[1];

            int startY = Math.max(0, y - h + 1);
            int endY = Math.min(y, m - h);
            int startX = Math.max(0, x - w + 1);
            int endX = Math.min(x, n - w);

            for (int i = startY; i <= endY; i++) {
                for (int j = startX; j <= endX; j++) {
                    map[i][j] = Math.min(map[i][j], k);
                }
            }
            k++;
        }

        int[] result = new int[]{0, 0, -1};

        for (int i = 0; i <= m - h; i++) {
            for (int j = 0; j <= n - w; j++) {
                if (map[i][j] == Integer.MAX_VALUE) {
                    return new int[]{i, j};
                }
                if (result[2] < map[i][j]) {
                    result[0] = i;
                    result[1] = j;
                    result[2] = map[i][j];
                }
            }
        }

        return new int[]{result[0], result[1]};
    }
}