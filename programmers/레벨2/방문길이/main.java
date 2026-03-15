package 레벨2.방문길이;

public class main {
    class Solution {
        public int solution(String dirs) {
            int answer = 0;
            // [현재 y][현재 x][다음 y][다음 x] 형태로 양방향 길을 직관적으로 기록
            boolean[][][][] visited = new boolean[11][11][11][11];

            int y = 5, x = 5; // 시작 위치

            // charAt 대신 toCharArray()를 사용해 향상된 for문으로 가독성 향상
            for (char dir : dirs.toCharArray()) {
                int ny = y;
                int nx = x;

                if (dir == 'U') ny++;
                else if (dir == 'D') ny--;
                else if (dir == 'R') nx++;
                else if (dir == 'L') nx--;

                // 1. 맵 밖으로 나가는 경우 무시 (코드 간소화)
                if (ny < 0 || ny > 10 || nx < 0 || nx > 10) continue;

                // 2. 처음 걸어보는 길인지 확인 (양방향 모두 체크)
                if (!visited[y][x][ny][nx]) {
                    visited[y][x][ny][nx] = true; // 정방향 방문 처리
                    visited[ny][nx][y][x] = true; // 역방향 방문 처리
                    answer++;
                }

                // 3. 현재 위치 업데이트
                y = ny;
                x = nx;
            }

            return answer;
        }
    }
}
