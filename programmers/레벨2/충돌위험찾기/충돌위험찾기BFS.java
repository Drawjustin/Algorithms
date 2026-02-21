package 레벨2.충돌위험찾기;

import java.util.*;

public class 충돌위험찾기BFS {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        Deque<Robot> deque = new ArrayDeque<>();

        for (int i = 0; i < routes.length; i++) {
            Robot robot = new Robot(routes[i], points);
            deque.add(robot);
        }

        while (!deque.isEmpty()) {
            int size = deque.size();
            Map<String, Integer> map = new HashMap<>();
            
            for (int i = 0; i < size; i++) {
                Robot robot = deque.poll();
                
                String pos = robot.y + "," + robot.x;
                map.put(pos, map.getOrDefault(pos, 0) + 1);
                
                if (!robot.isFinished()) {
                    robot.move();
                    deque.add(robot);
                }
            }
            
            for (int count : map.values()) {
                if (count > 1) {
                    answer++;
                }
            }
        }

        return answer;
    }

    static class Robot {
        int y, x;  // 현재 위치
        int targetY, targetX;  // 목표 위치
        int routeIdx;  // 다음 목표의 route 배열 인덱스
        int[] route;
        int[][] points;
        
        Robot(int[] route, int[][] points) {
            this.route = route;
            this.points = points;
            this.routeIdx = 1;  // 다음 목표는 route[1]부터
            
            // 시작 위치 설정
            int startIdx = route[0] - 1;
            this.y = points[startIdx][0];
            this.x = points[startIdx][1];
            
            // 첫 번째 목표 설정
            int targetIdx = route[1] - 1;
            this.targetY = points[targetIdx][0];
            this.targetX = points[targetIdx][1];
        }
        
        void move() {
            // y축 먼저 이동
            if (y > targetY) {
                y--;
            } else if (y < targetY) {
                y++;
            }
            // y축이 같으면 x축 이동
            else if (x > targetX) {
                x--;
            } else if (x < targetX) {
                x++;
            }
            
            // 목표에 도착했으면 다음 목표로 업데이트
            if (y == targetY && x == targetX) {
                routeIdx++;
                if (routeIdx < route.length) {
                    int targetIdx = route[routeIdx] - 1;
                    this.targetY = points[targetIdx][0];
                    this.targetX = points[targetIdx][1];
                }
            }
        }
        
        boolean isFinished() {
            return routeIdx >= route.length && y == targetY && x == targetX;
        }
    }
}
