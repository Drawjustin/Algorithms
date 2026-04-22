package 레벨2.힌트스테이지;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main2 {
    public class Solution{
        static Map<State,Integer> memo;
        static int n;
        static int [][] hints;
        static int [][] costs;
        public int solution(int[][] cost, int[][] hint) {
            n = cost.length;
            hints = hint;
            costs = cost;
            memo = new HashMap<>();
            int [] shint = new int[cost.length + 1];
            return dfs(1,shint);
        }

        public int dfs(int stage, int [] hint){
            if(stage > n)
                return 0;
            hint[stage] = Math.min(hint[stage], n - 1);
            State state = new State(stage,hint);

            Integer cached = memo.get(state);
            if (cached != null) {
                return cached;
            }

            int [] nextHint = hint.clone();
            nextHint[stage] = 0;
            int bestPrice = costs[stage - 1][hint[stage]] + dfs(stage +1, nextHint);

            if(stage < n) {
                int[] buyNextHint = hint.clone();
                buyNextHint[stage] = 0;
                int bundlePrice = hints[stage - 1][0];
                for (int i = 1; i < hints[stage - 1].length; i++) {
                    buyNextHint[hints[stage - 1][i]]++;
                }
                bestPrice = Math.min(bestPrice, costs[stage - 1][hint[stage]] + bundlePrice + dfs(stage + 1, buyNextHint));
            }
            memo.put(state, bestPrice);
            return bestPrice;
        }
        static class State{
            int stage;
            int [] hint;
            State(int stage, int[] hint){
                this.stage = stage;
                this.hint = hint.clone();
            }

            @Override
            public boolean equals(Object o){
                if(this == o) return true;
                if(!(o instanceof State)) return false;

                State other = (State) o;

                return stage == other.stage && Arrays.equals(other.hint,hint);
            }

            @Override
            public int hashCode(){
                return 31 * Integer.hashCode(stage) + Arrays.hashCode(hint);
            }

        }
    }

}