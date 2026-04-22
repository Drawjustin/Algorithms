package 레벨2.힌트스테이지;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class main {
    public class Solution {
        static int n;
        static int[][] cost;
        static int[][] hint;
        static Map<State, Integer> memo;
        public int solution(int[][] cost, int[][] hint) {
            this.n = cost.length;
            this.cost = cost;
            this.hint = hint;
            this.memo = new HashMap<>();
            int [] hints = new int [cost.length+1];

            return dfs(1, hints);

        }

        private static int dfs(int stage, int [] hints){
            if(stage > n){
                return 0;
            }
            normalize(hints, stage);

            State key = new State(stage, hints);
            Integer cached = memo.get(key);
            if (cached != null) {
                return cached;
            }

            int usableHints = hints[stage];
            int solveCost = cost[stage - 1][usableHints];

            int[] nextHints = hints.clone();

            int best = solveCost + dfs(stage + 1, nextHints);

            // 마지막 스테이지 전까지만 번들 구매 가능
            if (stage < n) {
                int[] buyHints = hints.clone();
                int bundlePrice = hint[stage - 1][0];

                for (int i = 1; i < hint[stage - 1].length; i++) {
                    int targetStage = hint[stage - 1][i];
                    buyHints[targetStage]++;
                }

                best = Math.min(best, solveCost + bundlePrice + dfs(stage + 1, buyHints));
            }

            memo.put(key, best);
            return best;
        }

        private static void normalize(int[] hints, int stage) {
            // 지난 스테이지 힌트는 의미 없으니 제거
            for (int i = 1; i < stage; i++) {
                hints[i] = 0;
            }

            // 각 스테이지에서 쓸 수 있는 힌트는 최대 n-1개
            for (int i = stage; i <= n; i++) {
                hints[i] = Math.min(hints[i], n - 1);
            }
        }

        private static class State{
            int level;
            int [] hints;
            public State(int level, int [] hints){
                this.level = level;
                this.hints = hints.clone();
            }
            @Override
            public boolean equals(Object o){
                if(this == o)
                    return true;
                if(!(o instanceof State)) return false;
                State other = (State) o;
                return other.level == this.level && Arrays.equals(hints,other.hints);
            }

            @Override
            public int hashCode(){
                int result = Integer.hashCode(level);
                result = 31 * result + Arrays.hashCode(hints);
                return result;
            }
        }
    }
}
