package 레벨2.숫자카드나누기;


public class main {
    class Solution{
        public int solution(int[] arrayA, int[] arrayB) {
            int answer = 0;


            int startA = arrayA[0];
            int startB = arrayB[0];

            for (int i = 1; i < arrayA.length; i++) {
                startA = gcd(arrayA[i], startA);
                startB = gcd(arrayB[i], startB);
            }


            for (int i = 0; i < arrayA.length; i++) {
                if(arrayA[i] % startB == 0){
                    startB = 0;
                    break;
                }
            }
            for (int i = 0; i < arrayA.length; i++) {
                if(arrayB[i] % startA == 0){
                    startA = 0;
                    break;
                }
            }

            answer = Math.max(startA,startB);
            return answer;
        }

        public static int gcd(int a, int b){
            return b == 0 ?  a : gcd(b, a % b);
        }
    }
}
