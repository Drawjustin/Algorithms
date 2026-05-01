package 레벨2.리프노드수최대화;

public class Main3 {
    class Solution {
        public int solution(int dist_limit, int split_limit) {
            long answer = 1;

            long pow2 = 1;
            for (int i = 0; pow2 <= split_limit ; i++, pow2 *= 2) {
                long pow3 = 1;
                for (int j = 0; pow2 * pow3 <= split_limit ; j++, pow3 *= 3) {
                    long leaf = pow2 * pow3;
                    long used2 = pow2 - 1;
                    long used3 = pow2 * ((pow3 - 1) / 2);
                    long used = used2 + used3;

                    if (used > dist_limit) continue;

                    long remain = dist_limit - used;

                    answer = Math.max(answer, leaf);

                    long can = Math.min(remain, leaf);

                    if(leaf * 2 <= split_limit){
                        answer = Math.max(answer, leaf + can);
                    }
                    if(leaf * 3 <= split_limit){
                        answer = Math.max(answer, leaf + can * 2);
                    }
                }
            }
            return (int) answer;
        }
    }
}
