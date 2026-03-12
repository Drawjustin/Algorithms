package 레벨2.멀쩡한사각형;

public class main {
    class Solution {
        public long solution(int w, int h) {
            long answer = 1;

            answer = (long) w * h - (w+h -gcd(w, h));

            return answer;
        }

        long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }
    }
}
