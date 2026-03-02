package 레벨2.두큐합같게만들기;

import java.util.ArrayDeque;
import java.util.Deque;

public class main {
        class Solution {
            public int solution(int[] queue1, int[] queue2) {
                int[] totalQueue = new int[queue1.length + queue2.length];
                long totalValue = 0;

                for (int i = 0; i < queue1.length; i++) {
                    totalQueue[i] = queue1[i];
                    totalQueue[i + queue1.length] = queue2[i];

                    totalValue += totalQueue[i];
                    totalValue += totalQueue[i + queue1.length];
                }

                if (totalValue % 2 != 0) {
                    return -1;
                }
                totalValue /= 2;

                // 1. 빈 큐가 아니라, 처음 queue1의 상태를 만들어두고 시작합니다.
                Deque<Integer> queue = new ArrayDeque<>();
                long sum = 0; // 원소 합은 int 범위를 넘을 수 있으니 무조건 long!

                for (int i = 0; i < queue1.length; i++) {
                    queue.offer(totalQueue[i]); // push가 아니라 offer! (뒤로 추가)
                    sum += totalQueue[i];
                }

                int count = 0; // 우리가 구하려는 이동 횟수
                int right = queue1.length; // totalQueue에서 다음에 가져올 원소의 인덱스
                int maxOperations = queue1.length * 4; // 무한루프 방지용 최대 횟수

                // 2. 인덱스 계산 없이 넣고 빼기만 하면서 count를 셉니다.
                while (count <= maxOperations) {
                    if (sum == totalValue) {
                        return count; // 골치 아픈 계산 없이, 찾은 즉시 횟수 리턴!
                    } else if (sum > totalValue) {
                        // 합이 더 크면 앞에서 뺍니다.
                        long polled = queue.poll();
                        sum -= polled;
                        count++; // 뺐으니까 1회 증가
                    } else {
                        // 합이 더 작으면 뒤에서 가져옵니다.
                        // % 연산을 쓰면 배열 끝을 넘어가도 다시 앞으로 돌아오며 원형 큐처럼 동작합니다.
                        int nextValue = totalQueue[right % totalQueue.length];
                        queue.offer(nextValue);
                        sum += nextValue;
                        right++;
                        count++; // 넣었으니까 1회 증가
                    }
                }

                return -1; // 최대 횟수를 넘기면 불가능한 경우
            }
        }
}