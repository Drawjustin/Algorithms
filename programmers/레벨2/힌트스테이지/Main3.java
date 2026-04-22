package 레벨2.힌트스테이지;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main3 {
    public class Solution {
        static Map<State,Integer> memo;
        static int [][] costs;
        static int [][] hints;
        public int solution(int[][] cost, int[][] hint) {
            costs = cost;
            hints = hint;
            memo = new HashMap<>();

            // 1 base
            int [] startHint = new int[cost.length + 1];
            return dfs(1,startHint);

        }

        private static int dfs(int stage, int [] hint){
            if(stage > costs.length)
                return 0;

            State state = new State(stage,hint);

            Integer cache = memo.get(state);
            if(cache != null)
                return cache;


            int [] nextStageNotBuyHint = hint.clone();
            nextStageNotBuyHint[stage] = 0;
            int bestValue = costs[stage -1][hint[stage]] + dfs(stage+1, nextStageNotBuyHint);


            if(stage < costs.length){
                int [] nextStageBuyHint = hint.clone();
                nextStageBuyHint[stage] = 0;
                int hintBundlePrice = hints[stage - 1][0];

                for (int i = 1; i < hints[stage -1].length; i++) {
                    nextStageBuyHint[hints[stage -1][i]]++;
                }


                for (int i = 1; i < nextStageBuyHint.length; i++) {
                    nextStageBuyHint[i] = Math.min(nextStageBuyHint[i], costs.length -1);
                }
                bestValue = Math.min(bestValue, costs[stage -1][hint[stage]] + hintBundlePrice + dfs(stage+1, nextStageBuyHint));
            }
            memo.put(state, bestValue);
            return bestValue;
        }
        public static class State {
            int stage;
            int [] hints;
            State(int stage, int [] hints){
                this.stage = stage;
                this.hints = hints.clone();
            }


            @Override
            public boolean equals(Object o){
                if(this == o)return true;
                if(!(o instanceof State)) return false;
                State other = (State) o;
                return other.stage == this.stage && Arrays.equals(hints,other.hints);
            }

            @Override
            public int hashCode(){
                return 31 * Integer.hashCode(this.stage) + Arrays.hashCode(hints);
            }

        }
    }
}
