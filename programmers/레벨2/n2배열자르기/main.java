package 레벨2.n2배열자르기;

public class main {
    class Solution {
        public int[] solution(int n, long left, long right) {
// 정답 배열의 크기는 (right - left + 1) 입니다.
            int length = (int) (right - left + 1);
            int[] answer = new int[length];

            // left부터 right까지 필요한 부분만 계산합니다.
            for (int i = 0; i < length; i++) {
                // 현재 구해야 하는 1차원 배열의 실제 인덱스
                long currentIndex = left + i;

                // 2차원 배열에서의 행과 열 계산
                long row = currentIndex / n;
                long col = currentIndex % n;

                // 둘 중 큰 값에 +1을 한 것이 해당 위치의 값
                answer[i] = (int) Math.max(row, col) + 1;
            }

            return answer;
        }
    }
}
