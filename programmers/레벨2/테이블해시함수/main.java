package 레벨2.테이블해시함수;

import java.util.Arrays;

public class main {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] == o2[col - 1]) {
                return o2[0] - o1[0]; // 2순위: 첫 번째 열 기준 내림차순
            }
            return o1[col - 1] - o2[col - 1]; // 1순위: col번째 열 기준 오름차순
        });


        for (int i = row_begin -1; i < row_end; i++) {
            int S_i = 0;
            for (int i1 : data[i]) {
                S_i += i1 % (i + 1);
            }


            if(answer == 0){
                answer = S_i;
                continue;
            }
            answer ^= S_i;
        }

        return answer;
    }
}
