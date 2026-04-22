package 레벨2.리프노드수최대화;

public class Main {
    class Solution {
        public int solution(int dist_limit, int split_limit) {
            int answer = 1;

            long pow2 = 1;
            for (int a = 0; pow2 <= split_limit; a++, pow2 *= 2) {
                long pow3 = 1;
                for (int b = 0; pow2 * pow3 <= split_limit; b++, pow3 *=3) {
                    long cur = pow2 * pow3;

                    long d2 = pow2 -1;
                    long d3 = pow2 * ((pow3 -1) / 2);

                    long d23 = d2 + d3;

                    if (d23 > dist_limit) {
                        continue;
                    }

                    answer = Math.max(answer, (int) cur);

                    long remain = dist_limit - d23;
                    long x = Math.min(cur, remain);

                    if(split_limit >= cur * 2){
                        answer = Math.max(answer, (int) (cur+x));
                    }

                    if(split_limit >= cur * 3){
                        answer = Math.max(answer, (int) (cur + x * 2));
                    }
                }
            }
            return answer;
        }
    }

}
