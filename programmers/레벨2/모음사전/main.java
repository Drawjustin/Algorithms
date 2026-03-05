package 레벨2.모음사전;

public class main {
    class Solution {
        int result = 0;
        int bk = 0;
        char [] words = new char[5];
        public int solution(String word) {
            int answer = 0;
            //
            StringBuilder stb = new StringBuilder();

            words[0] = 'A';
            words[1] = 'E';
            words[2] = 'I';
            words[3] = 'O';
            words[4] = 'U';

            dfs(stb,word);
            answer = result;
            return answer;
        }
        void dfs(StringBuilder stb, String word){
            if(bk == 1)
                return;
            if(stb.toString().equals(word)){
                bk = 1;
                return;
            }
            for (int i = 0; i < 5; i++) {
                if(stb.length() < 5){
                    result++;
                    dfs(stb.append(words[i]),word);
                    stb.deleteCharAt(stb.length()-1);
                }
            }
        }
    }
}
