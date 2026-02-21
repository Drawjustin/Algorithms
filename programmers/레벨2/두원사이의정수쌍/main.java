package 레벨2.두원사이의정수쌍;

public class main {
    class Solution {
        public long solution(int r1, int r2) {
            long answer = 0;

            answer += solve(r2,1);
            answer -= solve(r1,0);



            return answer;
        }

        public long solve(long r2,int key){
            long aim = 0;
            long now = 0;
            if(key ==1){
                for(long i= r2; i> 0; i--){
                    while(Math.sqrt(Math.pow(i,2)+Math.pow(now,2)) <= r2){
                        now++;
                    }
                    aim += (now-1)*4;
                }
                aim += r2*4+1;
            }
            else{
                for(long i= r2; i> 0; i--){
                    while(Math.sqrt(Math.pow(i,2)+Math.pow(now,2)) < r2){
                        now++;
                    }
                    aim += (now-1)*4;
                }
                aim += r2*4+1;
            }
            return aim;
        }


    }
}
