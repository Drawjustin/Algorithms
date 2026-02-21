package 레벨2.무인도여행;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

public class main {
    class Solution {
        public int[] solution(String[] maps) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            int y = maps.length;
            int x = maps[0].length();

            int[] dy = {0, 1, 0, -1};
            int[] dx = {1, 0, -1, 0};

            boolean[][] isVisited = new boolean[y][x];


            Deque<int[]> queue = new ArrayDeque<>();

            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    if (!(maps[i].charAt(j) == 'X') && !isVisited[i][j]) {
                        int totalFood = maps[i].charAt(j) - '0';
                        isVisited[i][j] = true;
                        queue.add(new int[]{i, j});
                        while (!queue.isEmpty()) {
                            int[] node = queue.poll();

                            for (int k = 0; k < 4; k++) {
                                int nxtY = node[0] + dy[k];
                                int nxtX = node[1] + dx[k];

                                if (0 > nxtY || nxtY >= y || 0 > nxtX || nxtX >= x)
                                    continue;
                                if (isVisited[nxtY][nxtX])
                                    continue;

                                if (maps[nxtY].charAt(nxtX) == 'X')
                                    continue;

                                isVisited[nxtY][nxtX] = true;
                                totalFood += maps[nxtY].charAt(nxtX) - '0';
                                queue.add(new int[]{nxtY, nxtX});
                            }
                        }
                        pq.add(totalFood);
                    }
                }
            }

            return pq.isEmpty() ? new int[]{-1} : pq.stream().sorted().mapToInt(Integer::intValue).toArray();
        }
    }
}
