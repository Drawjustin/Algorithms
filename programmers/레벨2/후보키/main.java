package 레벨2.후보키;

import java.util.*;

public class main {
    class Solution {
        public int solution(String[][] relation) {
            int row = relation.length;
            int col = relation[0].length;

            List<Integer> candidateKeys = new ArrayList<>();

            // 1 ~ (1 << col) - 1 : 모든 컬럼 조합
            for (int bit = 1; bit < (1 << col); bit++) {

                // 1. 최소성 검사
                if (!isMinimal(candidateKeys, bit)) {
                    continue;
                }

                // 2. 유일성 검사
                if (isUnique(relation, bit, row, col)) {
                    candidateKeys.add(bit);
                }
            }

            return candidateKeys.size();
        }

        // 최소성 검사
        private boolean isMinimal(List<Integer> candidateKeys, int bit) {
            for (int key : candidateKeys) {
                // 기존 후보키가 현재 bit의 부분집합이면 최소성 위반
                if ((key & bit) == key) {
                    return false;
                }
            }
            return true;
        }

        // 유일성 검사
        private boolean isUnique(String[][] relation, int bit, int row, int col) {
            Set<String> set = new HashSet<>();

            for (int r = 0; r < row; r++) {
                StringBuilder sb = new StringBuilder();

                for (int c = 0; c < col; c++) {
                    if ((bit & (1 << c)) != 0) {
                        sb.append(relation[r][c]).append("|");
                    }
                }

                set.add(sb.toString());
            }

            return set.size() == row;
        }
    }
}