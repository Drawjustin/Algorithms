package 레벨2.괄호회전하기;

import java.util.ArrayDeque;
import java.util.Deque;

public class main {
    class Solution {
        public int solution(String s) {
            int len = s.length();
            if (len % 2 != 0) return 0;

            int answer = 0;

            // s + s 를 만들지 않고 원본 s 하나만 그대로 씁니다.
            for (int i = 0; i < len; i++) {
                if (check(s, i, len)) {
                    answer++;
                }
            }
            return answer;
        }

        public boolean check(String s, int start, int len) {
            Deque<Character> stack = new ArrayDeque<>();

            for (int i = 0; i < len; i++) {
                // 핵심포인트: (시작점 + 현재거리) % 전체길이
                // 이렇게 하면 인덱스가 len을 넘어가도 다시 0으로 돌아옵니다.
                int index = (start + i) % len;
                char c = s.charAt(index);

                if (c == '[' || c == '{' || c == '(') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) return false;
                    char top = stack.pop();
                    if (c == ']' && top != '[') return false;
                    if (c == '}' && top != '{') return false;
                    if (c == ')' && top != '(') return false;
                }
            }
            return stack.isEmpty();
        }
    }
}
