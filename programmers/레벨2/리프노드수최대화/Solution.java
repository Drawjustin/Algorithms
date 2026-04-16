package 레벨2.리프노드수최대화;

class Solution {
    public int solution(int dist_limit, int split_limit) {
        int answer = 1;

        long pow2 = 1;
        for (int a = 0; pow2 <= split_limit; a++, pow2 *= 2) {
            long pow3 = 1;
            for (int b = 0; pow2 * pow3 <= split_limit; b++, pow3 *= 3) {
                long cur = pow2 * pow3;

                long used2 = pow2 - 1;
                long used3 = pow2 * ((pow3 - 1) / 2);
                long used = used2 + used3;

                if (used > dist_limit) {
                    continue;
                }

                answer = Math.max(answer, (int) cur);

                long remain = dist_limit - used;
                long x = Math.min(cur, remain);

                if (cur * 2 <= split_limit) {
                    answer = Math.max(answer, (int) (cur + x));
                }

                if (cur * 3 <= split_limit) {
                    answer = Math.max(answer, (int) (cur + 2 * x));
                }
            }
        }

        return answer;
    }
}
