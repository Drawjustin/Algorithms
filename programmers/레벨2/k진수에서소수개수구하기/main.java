package 레벨2.k진수에서소수개수구하기;

public class main {
    class Solution {
        public int solution(int n, int k) {
            int answer = 0;

            StringBuilder stb = new StringBuilder();

            while(n > 0){
                stb.append(n % k);
                n = n / k;
            }
            stb.reverse();

            String [] s = stb.toString().split("0+");

            for (String string : s) {
                if(isPrime(Long.parseLong(string))){
                    answer++;
                }
            }
            return answer;
        }

        public boolean isPrime(Long l){
            if(l < 2)
                return false;

            for (int i = 2; i <= Math.sqrt(l); i++) {
                if(l % i == 0)
                    return false;
            }
            return true;
        }
    }
}
