package Algorithms.이분탐색;

import java.util.List;

public class LowerBound {
    public static void main(String[] args) {
        /**
         * A이상의 값이 처음 나오는 Index 찾기
         * 1 2 [3] 4 5 6 7 8 9 10
         * 1l 2 [3] 4 5 6r 7 8 9 10
         * 1l 2 [3]r 4 5 6 7 8 9 10
         * 1 2l [3]lr 4 5r 6 7 8 9 10
         *
         * 1 [3] 3 4 5 6 7 8 9 10 11
         * 1l [3] 3 4 5 6r 7 8 9 10 11
         * 1l [3] 3r 4 5 6 7 8 9 10 11
         * 1l [3]r 3 4 5 6 7 8 9 10 11
         * 1 [3]lr 3 4 5r 6 7 8 9 10 11
         */

    }
    private int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size(); // 주의: size()-1 이 아니라 size() 입니다!

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) >= target) {
                right = mid; // 타겟 이상이면, 더 왼쪽(앞쪽)에도 있는지 찾아봄
            } else {
                left = mid + 1; // 타겟 미만이면, 무조건 오른쪽으로 가야 함
            }
        }
        return left;
    }

    /**
     * A초과의 값이 처음 나오는 Index 찾기
     * 1 2 [3] 4 5 6 7 8 9 10
     * 1l 2 [3] 4 5 6r 7 8 9 10
     * 1 2 [3] 4l 5 6r 7 8 9 10
     * 1 2 [3] 4l 5r 6 7 8 9 10
     * 1 2 [3] 4lr 5 6 7 8 9 10
     *
     * */

    private int upperBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) > target) { // ★ Lower Bound와 여기가 다름 (> 사용)
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * A이상 B이하
     * upper(B) - lower(A)
     *
     * A초과 B이하
     * upper(B) - upper(A)
     *
     * A이상 B미만
     * lower(B) - lower(A)
     *
     * A초과 B미만
     * lower(B) - upper(A)
     *
     */
}
