package 레벨2.모음사전;

public class main {
    class Solution {
        public int solution(String word) {
            // 단어가 한 글자씩 길어질 때마다 기본적으로 1씩 증가하므로 시작 값을 길이에 맞춤
            int answer = word.length();

            String vowels = "AEIOU";
            int[] weights = {781, 156, 31, 6, 1}; // 우리가 구한 자릿수별 가중치

            for (int i = 0; i < word.length(); i++) {
                // 현재 문자가 AEIOU 중 몇 번째인지 찾음 (A:0, E:1, I:2, O:3, U:4)
                int index = vowels.indexOf(word.charAt(i));

                // 해당 자리의 가중치 * 문자의 인덱스를 더해줌
                answer += weights[i] * index;
            }

            return answer;
        }
    }
}
