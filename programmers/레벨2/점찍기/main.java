package 레벨2.점찍기;

public class main {
    class Solution{
        public long solution(int k, int d) {
            long answer = 0;
            long t = (long) d;

            for(int i = 0; i <= d; i += k){
                double width = Math.sqrt(Math.pow(i, 2) + Math.pow(t, 2));
                if(width > d){
                    while(width > d){
                        t--;
                        width = Math.sqrt(Math.pow(i, 2) + Math.pow(t, 2));
                    }
                }
                // 여기서 y=0인 점들까지 다 더해줬으니!
                answer += t / k + 1;
            }

            // 밖에서 따로 더할 필요가 1도 없습니다.
            return answer;
        }
    }
}
