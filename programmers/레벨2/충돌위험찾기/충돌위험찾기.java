package 레벨2.충돌위험찾기;

import java.util.*;

public class 충돌위험찾기 {
    public static void main(String[] args) {

    }

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        // 각 로봇의 전체 경로를 저장
        List<List<int[]>> allPaths = new ArrayList<>();

        for (int i = 0; i < routes.length; i++) {  // points.length → routes.length
            List<int[]> path = new ArrayList<>();

            // 각 route의 모든 구간을 처리
            for (int j = 0; j < routes[i].length - 1; j++) {
                int fromIdx = routes[i][j] - 1;  // 1-based → 0-based
                int toIdx = routes[i][j + 1] - 1;

                int[] from = points[fromIdx].clone();
                int[] to = points[toIdx];

                // 시작점 추가 (첫 구간인 경우만)
                if (j == 0) {
                    path.add(from.clone());
                }

                // from에서 to까지 이동
                while (from[0] != to[0] || from[1] != to[1]) {
                    move(from, to);
                    path.add(from.clone());
                }
            }

            allPaths.add(path);
        }

        // 각 시간대별 충돌 체크
        int maxTime = allPaths.stream().mapToInt(List::size).max().orElse(0);

        for (int t = 0; t < maxTime; t++) {
            Map<String, Integer> positionCount = new HashMap<>();

            for (List<int[]> path : allPaths) {
                if (t < path.size()) {
                    int[] pos = path.get(t);
                    String key = pos[0] + "," + pos[1];
                    positionCount.put(key, positionCount.getOrDefault(key, 0) + 1);
                }
            }

            // 2개 이상 로봇이 있는 위치의 개수를 카운트
            for (int count : positionCount.values()) {
                if (count > 1) {
                    answer++;
                    // 또는 count-1을 더하려면: answer += count - 1;
                }
            }
        }

        return answer;
    }

    private void move(int[] from, int[] to) {
        // y축 먼저 이동
        if (from[0] > to[0]) {
            from[0]--;
        } else if (from[0] < to[0]) {
            from[0]++;
        }
        // y축이 같으면 x축 이동
        else if (from[1] > to[1]) {
            from[1]--;
        } else if (from[1] < to[1]) {
            from[1]++;
        }
    }
}
