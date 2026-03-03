package 레벨2.교점에별만들기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {

    class Solution {
        public String[] solution(int[][] line) {
            List<long[]> points = new ArrayList<>();

            long minX = Long.MAX_VALUE, minY = Long.MAX_VALUE;
            long maxX = Long.MIN_VALUE, maxY = Long.MIN_VALUE;

            for (int i = 0; i < line.length; i++) {
                long A = line[i][0];
                long B = line[i][1];
                long E = line[i][2];

                for (int j = i + 1; j < line.length; j++) {
                    long C = line[j][0];
                    long D = line[j][1];
                    long F = line[j][2];

                    long den = A * D - B * C;
                    if (den == 0) continue; // 평행 또는 일치

                    long xNum = B * F - E * D;
                    long yNum = E * C - A * F;

                    if (xNum % den != 0 || yNum % den != 0) continue; // 정수 교점만

                    long x = xNum / den;
                    long y = yNum / den;

                    points.add(new long[]{x, y});

                    minX = Math.min(minX, x);
                    minY = Math.min(minY, y);
                    maxX = Math.max(maxX, x);
                    maxY = Math.max(maxY, y);
                }
            }

            // 여기부터 별 찍기(2D char 배열 만들고 좌표 매핑)로 이어짐
            int width = (int)(maxX - minX + 1);
            int height = (int)(maxY - minY + 1);

            char[][] board = new char[height][width];
            for (int r = 0; r < height; r++) Arrays.fill(board[r], '.');

            for (long[] p : points) {
                int x = (int)(p[0] - minX);
                int y = (int)(maxY - p[1]); // y축 뒤집기(위가 maxY)
                board[y][x] = '*';
            }

            String[] answer = new String[height];
            for (int r = 0; r < height; r++) {
                answer[r] = new String(board[r]);
            }
            return answer;
        }
    }
}
