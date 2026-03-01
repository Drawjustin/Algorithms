package 레벨2.택배상자;

import java.util.Stack;

public class main {
    class Solution {
        public int solution(int[] order) {
            int answer = 0;
            int now = order[0];
            Stack<Integer> stack = new Stack<>();

            for (int i = 1; i < order[0]; i++) {
                stack.push(i);
            }

            for (int i = 0; i < order.length; i++) {
                if(order[i] == now){
                    answer++;
                    now++;
                }
                else if(!stack.isEmpty() && stack.peek() == order[i]){
                    stack.pop();
                    answer++;
                }
                else if(order[i] > now){
                    for (int j = now; j <order[i]; j++) {
                        stack.push(j);
                        now++;
                    }
                    answer++;
                    now++;
                }
                else{
                    break;
                }
            }
            return answer;
        }
    }
}
