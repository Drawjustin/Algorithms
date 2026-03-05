package 레벨2.빛의경로사이클;

import java.util.*;

public class main {

    class Solution {
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};

        public int[] solution(String[] grid) {
            List<Integer> list = new ArrayList<>();
            int R = grid.length;
            int C = grid[0].length();

            // visited[row][col][direction]
            boolean[][][] visited = new boolean[R][C][4];

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    for (int d = 0; d < 4; d++) {
                        // 아직 방문하지 않은 경로라면 새로운 사이클 시작
                        if (!visited[i][j][d]) {
                            int len = 0;
                            int r = i;
                            int c = j;
                            int dir = d;

                            // 이미 방문했던 경로(사이클의 시작점)로 돌아올 때까지 반복
                            while (!visited[r][c][dir]) {
                                visited[r][c][dir] = true;
                                len++;

                                // 1. 다음 칸으로 이동 (음수 인덱스를 방지하기 위해 길이를 더한 후 나머지 연산)
                                r = (r + dy[dir] + R) % R;
                                c = (c + dx[dir] + C) % C;

                                // 2. 도착한 칸의 문자에 따라 방향 전환
                                char node = grid[r].charAt(c);
                                if (node == 'L') {
                                    // 좌회전: 방향 인덱스 - 1 (음수 방지를 위해 3을 더함)
                                    dir = (dir + 3) % 4;
                                } else if (node == 'R') {
                                    // 우회전: 방향 인덱스 + 1
                                    dir = (dir + 1) % 4;
                                }
                                // 'S'인 경우는 직진이므로 dir을 그대로 둠
                            }

                            // 하나의 사이클이 완성되면 길이 추가
                            list.add(len);
                        }
                    }
                }
            }

            // 길이를 오름차순으로 정렬
            Collections.sort(list);

            // List를 int 배열로 변환
            return list.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
