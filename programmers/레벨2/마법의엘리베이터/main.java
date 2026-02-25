package 레벨2.마법의엘리베이터;

public class main {
    class Solution {
        public int solution(int storey) {
            int answer = 0;

            while (storey > 0) {
                int digit = storey % 10;        // 현재 자릿수 (1의 자리)
                int nextDigit = (storey / 10) % 10; // 다음 자릿수 (10의 자리)

                if (digit > 5) {
                    // 5보다 크면 위로 올라가는 게 이득 (10 - 현재 숫자)
                    answer += (10 - digit);
                    storey = storey / 10 + 1; // 윗자리로 올림 발생

                } else if (digit < 5) {
                    // 5보다 작으면 아래로 내려가는 게 이득
                    answer += digit;
                    storey = storey / 10;     // 올림 없음

                } else { // digit == 5
                    // 5일 때는 다음 자릿수를 보고 판단
                    answer += 5;
                    if (nextDigit >= 5) {
                        storey = storey / 10 + 1; // 다음 자릿수가 5이상이면 올림
                    } else {
                        storey = storey / 10;     // 아니면 내림
                    }
                }
            }

            return answer;
        }
    }
}
