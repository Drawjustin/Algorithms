package 지게차와크레인;

import java.util.*;

public class 지게차와크레인 {
    static char[][] map;
    static boolean[][] isVisited;
    static int[] xAry = {1, 0, -1, 0};
    static int[] yAry = {0, 1, 0, -1};
    
    public static int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();
        map = new char[n + 2][m + 2];
        isVisited = new boolean[n + 2][m + 2];
        
        // 맵 초기화 (패딩 포함)
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], ' ');
        }
        
        for (int i = 0; i < n; i++) {
            String data = storage[i];
            for (int k = 0; k < m; k++) {
                map[i + 1][k + 1] = data.charAt(k);
            }
        }
        
        // 요청 처리
        for (String request : requests) {
            char order = request.charAt(0);
            boolean isCrane = (request.length() == 2);
            
            if (isCrane) {
                // 크레인: 모든 해당 문자 제거
                for (int j = 0; j < map.length; j++) {
                    for (int k = 0; k < map[j].length; k++) {
                        if (map[j][k] == order) {
                            map[j][k] = ' ';
                        }
                    }
                }
            } else {
                // 지게차: 외부와 연결된 해당 문자만 제거
                bfs(order);
            }
        }
        
        // 남은 컨테이너 개수 세기
        int result = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] != ' ') {
                    result++;
                }
            }
        }
        return result;
    }
    
    private static void bfs(char order) {
        // 방문 배열 초기화
        for (int i = 0; i < isVisited.length; i++) {
            Arrays.fill(isVisited[i], false);
        }
        
        Deque<int[]> queue = new ArrayDeque<>();
        List<int[]> targets = new ArrayList<>();  // 제거할 대상 목록
        
        // (0,0)은 패딩된 외부 공간
        queue.add(new int[]{0, 0});
        isVisited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cy = current[0];
            int cx = current[1];
            
            for (int i = 0; i < 4; i++) {
                int nxtY = cy + yAry[i];
                int nxtX = cx + xAry[i];
                
                // 범위 체크
                if (!isMoved(nxtY, nxtX)) {
                    continue;
                }
                
                // 이미 방문한 곳
                if (isVisited[nxtY][nxtX]) {
                    continue;
                }
                
                isVisited[nxtY][nxtX] = true;
                
                // 빈 공간이면 계속 탐색
                if (map[nxtY][nxtX] == ' ') {
                    queue.add(new int[]{nxtY, nxtX});
                }
                // 타겟 문자면 제거 대상으로 등록만 하고 더 이상 탐색 X
                else if (map[nxtY][nxtX] == order) {
                    targets.add(new int[]{nxtY, nxtX});
                    // 주의: 큐에 추가하지 않음! (그 너머는 이번 턴에 접근 불가)
                }
                // 다른 문자면 무시 (탐색도 제거도 안 함)
            }
        }
        
        // 탐색 완료 후 일괄 제거
        for (int[] target : targets) {
            map[target[0]][target[1]] = ' ';
        }
    }
    
    private static boolean isMoved(int y, int x) {
        return 0 <= y && y < map.length && 0 <= x && x < map[y].length;
    }
    
    public static void main(String[] args) {
        // 테스트 케이스 1: 재귀적 제거 버그 테스트
        String[] storage1 = {"BBB", "BBB", "BBB"};
        String[] requests1 = {"B"};
        int result1 = solution(storage1, requests1);
        System.out.println("Test 1 - Result: " + result1 + " (Expected: 1, 중앙 B만 남아야 함)");
        
        // 테스트 케이스 2: 모두 제거
        String[] storage2 = {"AA", "AA"};
        String[] requests2 = {"A"};
        int result2 = solution(storage2, requests2);
        System.out.println("Test 2 - Result: " + result2 + " (Expected: 0, 모두 제거)");
        
        // 테스트 케이스 3: 크레인 사용
        String[] storage3 = {"ABC", "DEF", "GHI"};
        String[] requests3 = {"EE"};  // 크레인으로 E 모두 제거
        int result3 = solution(storage3, requests3);
        System.out.println("Test 3 - Result: " + result3 + " (Expected: 8, E만 제거)");
    }
}
