package 레벨2.리프노드수최대화;

public class Main2 {
    public class Solution{
        public int solution(int dist_limit, int split_limit) {
            long pow2 = 1;
            int result = 1;
            for (int i = 0; pow2 * 2 <= split_limit; i++, pow2 *= 2) {
                long pow3 = 1;
                for (int j = 0; pow2 * pow3 <= split_limit ; j++, pow3 *= 3) {
                    // 총 리프노드 수
                    long cur = pow2 * pow3;
                    // 중간노드 수
                    long used2 = pow2 - 1;
                    long used3 = pow2 * (pow3 - 1) / 2;
                    long used23 = used2 + used3;

                    if(used23 > dist_limit)
                        continue;


                    result = Math.max(result, (int) cur);

                    long remain = dist_limit - used23;
                    long x = Math.min(cur, remain);


                    if(cur * 2 <= split_limit){
                        result = Math.max(result, (int) (cur+x));
                    }

                    if(cur * 3 <= split_limit){
                        result = Math.max(result, (int) (cur+ x *2));
                    }

                }
            }
            return result;
        }
    }
}
