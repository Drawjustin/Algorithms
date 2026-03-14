package 레벨2.문자열압축;

public class main {
    class Solution {
        public int solution(String s) {
            int answer = s.length();

            for (int split = 1; split <= s.length() / 2; split++) {

                String prev = s.substring(0, split);
                int count = 1;
                int len = 0;

                for (int i = split; i < s.length(); i += split) {

                    int end = Math.min(i + split, s.length());
                    String cur = s.substring(i, end);

                    if (prev.equals(cur)) {
                        count++;
                    } else {
                        if (count > 1) len += String.valueOf(count).length();
                        len += prev.length();

                        prev = cur;
                        count = 1;
                    }
                }

                if (count > 1) len += String.valueOf(count).length();
                len += prev.length();

                answer = Math.min(answer, len);
            }

            return answer;
        }
    }
}
