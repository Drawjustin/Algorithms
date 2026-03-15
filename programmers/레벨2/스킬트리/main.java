package 레벨2.스킬트리;

public class main {
    class Solution {
        public int solution(String skill, String[] skill_trees) {
            int answer = 0;

            for (String skillTree : skill_trees) {
                // 1. skill에 포함되지 않은 모든 스킬을 문자열에서 제거해버립니다.
                String filtered = skillTree.replaceAll("[^" + skill + "]", "");

                // 2. 남은 스킬 순서가 원래 skill의 접두사(처음부터 시작)인지 확인합니다.
                if (skill.startsWith(filtered)) {
                    answer++;
                }
            }

            return answer;
        }
    }
}
