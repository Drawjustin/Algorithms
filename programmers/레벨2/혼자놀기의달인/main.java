package 레벨2.혼자놀기의달인;

import java.util.Collections;
import java.util.PriorityQueue;

public class main {
    class Solution {
        public int solution(int[] cards) {
            int n = cards.length;
            boolean[] isOpened = new boolean[n]; // 소문자 boolean! 기본값 false

            // 🚀 가장 큰 값부터 뽑아내는 Max-Heap으로 선언!
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            // 0번 상자부터 순서대로 확인
            for (int i = 0; i < n; i++) {
                if (!isOpened[i]) {
                    int count = 0; // 이번 사이클에서 열린 상자 개수
                    int curr = i;  // 현재 열어볼 상자 인덱스

                    // 이미 열린 상자를 만날 때까지 계속 열기 (Iterative DFS)
                    while (!isOpened[curr]) {
                        isOpened[curr] = true; // 상자 열기
                        count++;               // 개수 추가
                        curr = cards[curr] - 1; // 다음 상자 인덱스로 이동 (1을 빼서 0-based로 맞춤)
                    }

                    // 탐색이 끝난 후, 상자를 1개라도 열었다면 큐에 저장
                    if (count > 0) {
                        pq.add(count);
                    }
                }
            }

            // 🏆 그룹이 1개밖에 없으면 조건에 따라 0점 반환
            if (pq.size() < 2) {
                return 0;
            }

            // 가장 큰 그룹 2개를 뽑아서 곱하기
            return pq.poll() * pq.poll();
        }
    }
}
