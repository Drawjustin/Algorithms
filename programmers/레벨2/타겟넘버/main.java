package 레벨2.타겟넘버;

public class main {
    class Solution {
        int answer = 0;
        public int solution(int[] numbers, int target) {

            dfs(numbers,target,0,0);

            return answer;
        }

        public void dfs(int[] numbers, int target, int start, int sum) {
            if(start == numbers.length ) {
                if(target == sum)
                    answer++;
                return;
            }
            dfs(numbers, target, start + 1, sum + numbers[start]);
            dfs(numbers, target, start + 1, sum - numbers[start]);
        }
    }
}
