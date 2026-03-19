package 레벨2.카펫;

public class main {
    class Solution {
        public int[] solution(int brown, int yellow) {
            int total = brown + yellow;

            for (int y = 3; y <= Math.sqrt(total); y++) {
                if (total % y != 0) continue;

                int x = total / y;

                if ((x - 2) * (y - 2) == yellow) {
                    return new int[]{x, y};
                }
            }

            return new int[]{};
        }
    }
}
