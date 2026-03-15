package 레벨2.HIndex;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class main {
    class Solution {
        public int solution(int[] citations) {
            int answer = 0;

            List<Integer> list = new ArrayList<>();
            for (int citation : citations) {
                list.add(citation);
            }

            list.sort(Comparator.reverseOrder());


            for (int i = citations.length; i > 0; i--) {
                int curUsed = 0;
                for (int j = 0; j < citations.length; j++) {
                    if(list.get(j) >= i){
                        curUsed++;
                    }
                }
                if(curUsed >= i){
                    answer = i;
                    break;
                }
            }

            return answer;
        }
    }
}
