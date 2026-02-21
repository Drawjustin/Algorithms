package 레벨2.요격시스템;

import java.util.Arrays;
import java.util.Comparator;

public class 요격시스템 {
    public int solution(int[][] targets) {

        Arrays.sort(targets, new Comparator<int []>(){
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) return o1[0] - o2[0];

                return o1[1] - o2[1];
            }
        });

        int result = 0;
        int curX = 0;
        for (int i = 0; i < targets.length; i++) {
            if(curX <= targets[i][0]){
                curX = targets[i][1];
                result++;
            }
        }
        return result;
    }
}
