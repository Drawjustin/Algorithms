package 레벨2.롤케이크자르기;

import java.util.HashMap;
import java.util.Map;

public class main {
    class Solution{
        public int solution(int[] topping) {
            int answer = 0;

            Map<Integer,Integer> rightMap = new HashMap<>();
            Map<Integer,Integer> leftMap = new HashMap<>();

            for (int t : topping) {
                rightMap.put(t, rightMap.getOrDefault(t, 0) + 1);
            }

            int left = 0;
            int right = rightMap.size();


            for (int i = 0; i <topping.length ; i++) {
                int curNode = rightMap.get(topping[i]);

                if(leftMap.containsKey(topping[i])){
                    leftMap.compute(topping[i], (k, value) -> value + 1);
                }else{
                    leftMap.put(topping[i],1);
                    left++;
                }

                if(curNode == 1){
                    rightMap.remove(topping[i]);
                    right--;
                }else{
                    rightMap.put(topping[i],curNode -1);
                }

                if(left == right)
                    answer++;
            }

            return answer;
        }
    }
}
