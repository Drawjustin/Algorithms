package 레벨2.두개이하로다른비트;

public class main {
    class Solution {
        public long[] solution(long[] numbers) {
            long[] answer = new long[numbers.length];

            for (int i = 0; i < numbers.length; i++) {
                long x = numbers[i];

                if (x % 2 == 0) {
                    // 짝수인 경우: 그냥 1만 더하면 됨
                    answer[i] = x + 1;
                } else {
                    // 홀수인 경우: 비트 마법 사용
                    long lowestZero = (x + 1) & ~x; // 가장 뒤쪽에 있는 0의 값을 추출
                    answer[i] = x + (lowestZero / 2); // 해당 0을 1로, 그 오른쪽 1을 0으로 뒤집음
                }
            }

            return answer;
        }
    }
//    public long[] solution(long[] numbers) {
//        long[] answer = new long[numbers.length];
//
//        for (int i = 0; i < numbers.length; i++) {
//            long x = numbers[i];
//
//            if (x % 2 == 0) {
//                answer[i] = x + 1;
//            } else {
//                // 1. 이진수 문자열로 변환 (맨 앞에 "0"을 붙여서 자릿수 넘어가는 것 대비)
//                String bin = "0" + Long.toBinaryString(x);
//
//                // 2. 가장 마지막에 있는 '0'의 인덱스 찾기
//                int zeroIdx = bin.lastIndexOf("0");
//
//                // 3. StringBuilder로 문자열 조작
//                StringBuilder sb = new StringBuilder(bin);
//                sb.setCharAt(zeroIdx, '1');       // 마지막 0을 1로
//                sb.setCharAt(zeroIdx + 1, '0');   // 그 바로 오른쪽 1을 0으로
//
//                // 4. 다시 10진수 숫자로 변환해서 저장
//                answer[i] = Long.parseLong(sb.toString(), 2);
//            }
//        }
//        return answer;
//    }
}
