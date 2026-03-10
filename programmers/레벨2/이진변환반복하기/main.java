package 레벨2.이진변환반복하기;

public class main {
    class Solution {
        public int[] solution(String s) {
            int[] answer = new int[2];
            int transCount = 0;
            int zeroCount = 0;

            while (!s.equals("1")) {
                int oneCount = 0;
                transCount++;

                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '1') {
                        oneCount++;
                    } else {
                        zeroCount++;
                    }
                }

                s = Integer.toBinaryString(oneCount);
            }

            answer[0] = transCount;
            answer[1] = zeroCount;
            return answer;
        }
    }
}
