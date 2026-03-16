package 레벨2.타겟넘버;

public class main {
    class Solution {
        public int solution(int[] numbers, int target) {
            return countWays(numbers, target, 0, 0);
        }

        private int countWays(int[] numbers, int target, int index, int sum) {
            if (index == numbers.length) {
                return sum == target ? 1 : 0;
            }

            int addCase = countWays(numbers, target, index + 1, sum + numbers[index]);
            int subtractCase = countWays(numbers, target, index + 1, sum - numbers[index]);

            return addCase + subtractCase;
        }
    }
}
