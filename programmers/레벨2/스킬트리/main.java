package 레벨2.스킬트리;

public class main {
    class Solution {
        public int solution(String skill, String[] skill_trees) {
            int answer = 0;

            for (String skillTree : skill_trees) {
                int skillIndex = 0; // 현재 배워야 할 스킬의 인덱스
                boolean isValid = true;

                for (char c : skillTree.toCharArray()) {
                    // 현재 문자가 필수 스킬에 포함되어 있는지 확인
                    if (skill.indexOf(c) != -1) {
                        // 포함되어 있다면, 지금 배워야 할 순서가 맞는지 확인
                        if (skill.charAt(skillIndex) == c) {
                            skillIndex++; // 다음 스킬로 순서 넘어감
                        } else {
                            isValid = false; // 순서가 틀렸다면 즉시 종료
                            break;
                        }
                    }
                }

                if (isValid) answer++;
            }

            return answer;
        }
    }
}
