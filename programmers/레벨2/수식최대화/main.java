package 레벨2.수식최대화;

import java.util.ArrayList;
import java.util.List;

public class main {
    class Solution {
        public long solution(String expression) {
            long answer = 0;

            answer = Math.max(answer, calculator(expression,"-","*","+"));
            answer = Math.max(answer, calculator(expression,"-","+","*"));
            answer = Math.max(answer, calculator(expression,"+","*","-"));
            answer = Math.max(answer, calculator(expression,"+","-","*"));
            answer = Math.max(answer, calculator(expression,"*","+","-"));
            answer = Math.max(answer, calculator(expression,"*","-","+"));


            return answer;
        }
        long calculator(String expression, String first, String second, String third) {
            List<Long> numbers = new ArrayList<>();
            List<String> operators = new ArrayList<>();

            // 1. expression 파싱
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);

                if (ch == '+' || ch == '-' || ch == '*') {
                    numbers.add(Long.parseLong(sb.toString()));
                    operators.add(String.valueOf(ch));
                    sb.setLength(0);
                } else {
                    sb.append(ch);
                }
            }
            numbers.add(Long.parseLong(sb.toString())); // 마지막 숫자

            // 2. 우선순위대로 계산
            calculateByPriority(numbers, operators, first);
            calculateByPriority(numbers, operators, second);
            calculateByPriority(numbers, operators, third);

            // 3. 최종 값 절댓값 반환
            return Math.abs(numbers.get(0));
        }

        public void calculateByPriority(List<Long> numbers, List<String> operators, String op) {
            for (int i = 0; i < operators.size(); ) {
                if (operators.get(i).equals(op)) {
                    long a = numbers.get(i);
                    long b = numbers.get(i + 1);
                    long result = calculate(a, b, op);

                    numbers.remove(i + 1);
                    numbers.remove(i);
                    numbers.add(i, result);

                    operators.remove(i);
                } else {
                    i++;
                }
            }
        }

        public long calculate(long a, long b, String op) {
            if (op.equals("+")) {
                return a + b;
            } else if (op.equals("-")) {
                return a - b;
            } else {
                return a * b;
            }
        }
    }
}
