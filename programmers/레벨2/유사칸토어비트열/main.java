package 레벨2.유사칸토어비트열;

public class main {
    class Solution {
        public int solution(int n, long l, long r) {
            // r번째까지의 1의 개수 - (l - 1)번째까지의 1의 개수
            return (int) (countOnes(n, r) - countOnes(n, l - 1));
        }

        // 처음부터 k번째(길이 k)까지의 1의 개수를 구하는 재귀 함수
        private long countOnes(int n, long k) {
            if (n == 0) {
                return k; // n이 0이면 길이가 1이고, 값은 '1'이므로 k개 반환
            }
            if (k == 0) {
                return 0; // 길이가 0이면 1의 개수도 0
            }

            long chunkSize = (long) Math.pow(5, n - 1);  // 5등분 했을 때 한 덩어리의 길이
            long onesPerChunk = (long) Math.pow(4, n - 1); // 한 덩어리 안에 들어있는 1의 개수

            long chunkIdx = k / chunkSize; // k가 속한 덩어리 인덱스 (0, 1, 2, 3, 4)
            long remain = k % chunkSize;   // 해당 덩어리 안에서의 나머지 길이

            // 11 0 11 패턴 처리
            if (chunkIdx < 2) {
                // 0, 1번째 덩어리 (앞쪽 '11' 구간)
                return chunkIdx * onesPerChunk + countOnes(n - 1, remain);
            } else if (chunkIdx == 2) {
                // 2번째 덩어리 (가운데 '0' 구간)
                // 앞선 2개의 덩어리('11')에 포함된 1만 더해주고, 나머지는 다 0이므로 재귀 종료
                return 2 * onesPerChunk;
            } else {
                // 3, 4번째 덩어리 (뒤쪽 '11' 구간)
                // 가운데 0 덩어리(1개)를 빼고 계산해야 하므로 (chunkIdx - 1)
                return (chunkIdx - 1) * onesPerChunk + countOnes(n - 1, remain);
            }
        }
    }
}
