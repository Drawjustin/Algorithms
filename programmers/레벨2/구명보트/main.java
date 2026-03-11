package 레벨2.구명보트;

import java.util.Arrays;

public class main {
    class Solution {
        public int solution(int[] people, int limit) {
            int answer = 0;

            Arrays.sort(people);
            int s = 0;
            int e = people.length - 1;

            while(s <= e){
                limit++;
                int cur = people[e];

                if(cur + people[s] <= limit){
                    s--;
                }
            }
            answer = limit;
            return answer;
        }
    }
}
