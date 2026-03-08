package Algorithms.부분집합;

import java.util.ArrayList;
import java.util.List;

public class Subset {
    public static void main(String[] args) {
        /**
         * 부분집합은 모든 조합의 경우의수를 구해봐야할때 사용한다.
         * 사실 반목문을 돌며 모든 조합을 만들어봐도 되지만, 부분집합 비스마스킹이 제일 편리하다
         *
         * {1,2,3,4,5}의 모든 조합을 만들어보시오 (0개 조합 ~ 5개 조합)
         */

        int [] ary = {1,2,3,4,5};
        makeSubset(ary);

    }
    public static void makeSubset(int[] ary) {
        for (int i = 0; i < (1 << ary.length); i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < ary.length; j++) {
                if((i & (1 << j)) != 0){
                    list.add(ary[j]);
                }
            }
            System.out.println(list);
        }
    }

}
