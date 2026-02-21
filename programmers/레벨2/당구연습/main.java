package 레벨2.당구연습;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class main {
    class Solution {
        public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
            int[] answer = new int[balls.length];


            // Target공과 벽, 나의 공과 벽간의 길이의 합이 가장 짧은 벽을 찾는다.
            // 그리고 Target공을 대칭이동 시킨다.
            // 단 경로에 target공이 있을경우 해당벽은 제외

            List<Integer> list = new ArrayList<>();
            List<Integer> result = new ArrayList<>();


            for (int[] ball : balls) {
                //ball = target
                List<Integer> tempList = new ArrayList<>();
                //좌상우하 순으로 조건검사
                if (!(startY == ball[1] && startX > ball[0])) {
                    int diffY = Math.abs(ball[1] - startY);
                    int diffX = Math.abs(-ball[0] - startX);
                    tempList.add((int) (Math.pow(diffY, 2) + Math.pow(diffX, 2)));
                }
                if (!(startX == ball[0] && startY < ball[1])) {
                    int diffY = Math.abs(ball[1] + (n - ball[1]) * 2 - startY);
                    int diffX = Math.abs(ball[0] - startX);
                    tempList.add((int) (Math.pow(diffY, 2) + Math.pow(diffX, 2)));
                }
                if (!(startY == ball[1] && startX < ball[0])) {
                    int diffY = Math.abs(ball[1] - startY);
                    int diffX = Math.abs(ball[0] + (m - ball[0]) * 2 - startX);
                    tempList.add((int) (Math.pow(diffY, 2) + Math.pow(diffX, 2)));
                }
                if (!(startX == ball[0] && startY > ball[1])) {
                    int diffY = Math.abs(-ball[1] - startY);
                    int diffX = Math.abs(ball[0] - startX);
                    tempList.add((int) (Math.pow(diffY, 2) + Math.pow(diffX, 2)));
                }
                Collections.sort(tempList);
                result.add(tempList.getFirst());
            }
            answer = result.stream().mapToInt(Integer::intValue).toArray();
            return answer;
        }

    }
}
