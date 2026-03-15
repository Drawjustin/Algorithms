package 레벨2.스킬트리;

public class main {
    class Solution {
        public int solution(String skill, String[] skill_trees) {
            int answer = 0;



            for (int i = 0; i < skill_trees.length; i++) {
                int [][] node = new int [101][2];

                for (int q = 0; q < skill.length() - 1; q++) {
                    node[skill.charAt(q)][0] = -1;
                    node[skill.charAt(q)][1] = skill.charAt(q+1);
                }

                node[skill.charAt(skill.length() - 1)][0] = -1;
                node[skill.charAt(0)][0] = 1;

                String cur =  skill_trees[i];

                int flag = 1;
                for (int j = 0; j < cur.length(); j++) {
                    if(node[cur.charAt(j)][0] < 0) {
                        flag = -1;
                        break;
                    }
                    else if(node[cur.charAt(j)][0] == 1) {
                        node[node[cur.charAt(j)][1]][0] = 1;
                    }
                }
                System.out.println(i +" " + flag);
                if(flag == 1){
                    answer++;
                }
            }

            return answer;
        }
    }
}
