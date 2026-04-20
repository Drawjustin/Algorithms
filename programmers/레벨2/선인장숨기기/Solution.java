package 레벨2.선인장숨기기;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
    static final int INF = 1_000_000_000;

    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[][] time = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(time[i], INF);
        }

        for (int i = 0; i < drops.length; i++) {
            int y = drops[i][0];
            int x = drops[i][1];
            time[y][x] = Math.min(time[y][x], i + 1);
        }

        int[][] rowMin = new int[m][n - w + 1];

        for (int i = 0; i < m; i++) {
            Deque<Integer> dq = new ArrayDeque<>();

            for (int j = 0; j < n; j++) {
                while (!dq.isEmpty() && time[i][dq.peekLast()] >= time[i][j]) {
                    dq.pollLast();
                }
                dq.offerLast(j);

                while (!dq.isEmpty() && dq.peekFirst() <= j - w) {
                    dq.pollFirst();
                }

                if (j >= w - 1) {
                    rowMin[i][j - w + 1] = time[i][dq.peekFirst()];
                }
            }
        }

        int best = -1;
        int bestY = Integer.MAX_VALUE;
        int bestX = Integer.MAX_VALUE;

        for (int j = 0; j <= n - w; j++) {
            Deque<Integer> dq = new ArrayDeque<>();

            for (int i = 0; i < m; i++) {
                while (!dq.isEmpty() && rowMin[dq.peekLast()][j] >= rowMin[i][j]) {
                    dq.pollLast();
                }
                dq.offerLast(i);

                while (!dq.isEmpty() && dq.peekFirst() <= i - h) {
                    dq.pollFirst();
                }

                if (i >= h - 1) {
                    int startY = i - h + 1;
                    int value = rowMin[dq.peekFirst()][j];

                    if (value > best) {
                        best = value;
                        bestY = startY;
                        bestX = j;
                    } else if (value == best) {
                        if (startY < bestY || (startY == bestY && j < bestX)) {
                            bestY = startY;
                            bestX = j;
                        }
                    }
                }
            }
        }

        return new int[]{bestY, bestX};
    }
}