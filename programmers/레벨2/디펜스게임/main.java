package 레벨2.디펜스게임;

import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        // 내림차순 우선순위 큐 (가장 많은 적의 수를 빠르게 뽑기 위함)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        long now = 0L; // 지금까지 만난 누적 적의 수

        for (int i = 0; i < enemy.length; i++) {
            now += enemy[i];
            pq.add(enemy[i]);

            // 1. 병사가 부족해졌다면 (위기 발생!)
            if (now > n) {
                // 2. 무적권이 남아있다면
                if (k > 0) {
                    int curBig = pq.poll(); // 지금까지 만난 가장 큰 적의 무리를 무적권으로 방어!
                    now -= curBig;          // 깎였던 병사 수 복구
                    k--;
                }
                // 3. 무적권도 없다면 게임 오버
                else {
                    return i + 1; // i번째 라운드에서 졌으므로, 통과한 라운드 수는 딱 i개가 됨
                }
            }
        }

        // 반복문을 무사히 다 통과했다면 모든 라운드를 클리어한 것
        return enemy.length;
    }
}