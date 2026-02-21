package 레벨2.뒤에있는큰수찾기;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class main {
//    public static void main(String[] args) {
//        Solution s = new Solution();
//        System.out.println(Arrays.toString(s.solution(new int[]{9, 1, 5, 3,6,2})));
//    }
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = numbers.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= numbers[i]) {
                stack.pop();
            }

            answer[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(numbers[i]);
        }

        return answer;
    }
}
}
