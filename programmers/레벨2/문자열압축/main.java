package 레벨2.문자열압축;

import java.util.HashMap;
import java.util.Map;

public class main {
    class Solution {
        public int solution(String s) {
            int answer = s.length();

            for (int split = 1; split <= s.length() / 2; split++) {

                String prev = "";
                int count = 1;
                int len = 0;

                for (int i = 0; i < s.length(); i += split) {

                    int end = Math.min(i + split, s.length());
                    String cur = s.substring(i, end);

                    if (prev.equals(cur)) {
                        count++;
                    } else {
                        if (!prev.equals("")) {
                            len += prev.length();
                            if (count > 1) len += String.valueOf(count).length();
                        }
                        prev = cur;
                        count = 1;
                    }
                }

                len += prev.length();
                if (count > 1) len += String.valueOf(count).length();

                answer = Math.min(answer, len);
            }

            return answer;
        }
    }
}
