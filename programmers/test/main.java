package test;

public class main {
    class Solution {
        public int solution(String s) {
            int answer =  s.length();


            for (int i = 1; i <= s.length() / 2; i++) {
                String prev = s.substring(0,i);
                int len = 0;
                int count = 1;
                for (int j = i; j < s.length(); j+= i) {
                    int end = Math.min(j+i,s.length());

                    String cur = s.substring(j,end);

                    if(prev.equals(cur)){
                        count++;
                    }else{
                        if(count > 1){
                            len += String.valueOf(count).length();
                        }
                        count = 1;
                        len += i;
                        prev = cur;
                    }
                }
                if(count > 1){
                    len += String.valueOf(count).length();
                }
                len += prev.length();
                answer = Math.min(len, answer);
            }

            return answer;
        }
    }
}
