package 레벨2.힌트스테이지;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution2 {
    private int n;
    private int[][] cost;
    private int[][] hint;
    private Map<State, Integer> memo;

    public int solution(int[][] cost, int[][] hint) {
        this.n = cost.length;
        this.cost = cost;
        this.hint = hint;
        this.memo = new HashMap<>();

        return dfs(1, new int[n + 1]);
    }

    private int dfs(int stage, int[] hints) {
        if (stage > n) {
            return 0;
        }

        State key = new State(stage, hints, n);
        Integer cached = memo.get(key);
        if (cached != null) {
            return cached;
        }

        int solveCost = cost[stage - 1][hints[stage]];

        int best = solveCost + dfs(stage + 1, advanceHints(hints, stage));

        if (stage < n) {
            int[] buyHints = advanceHints(hints, stage);
            int bundlePrice = hint[stage - 1][0];

            for (int i = 1; i < hint[stage - 1].length; i++) {
                int targetStage = hint[stage - 1][i];
                buyHints[targetStage] = Math.min(buyHints[targetStage] + 1, n - 1);
            }

            best = Math.min(best, solveCost + bundlePrice + dfs(stage + 1, buyHints));
        }

        memo.put(key, best);
        return best;
    }

    private int[] advanceHints(int[] hints, int stage) {
        int[] next = hints.clone();
        next[stage] = 0;
        return next;
    }

    static class State {
        int stage;
        int[] hints;

        State(int stage, int[] hints, int n) {
            this.stage = stage;
            this.hints = new int[n + 1];
            for (int i = stage; i <= n; i++) {
                this.hints[i] = Math.min(hints[i], n - 1);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State)) return false;
            State other = (State) o;
            return stage == other.stage && Arrays.equals(hints, other.hints);
        }

        @Override
        public int hashCode() {
            int result = Integer.hashCode(stage);
            result = 31 * result + Arrays.hashCode(hints);
            return result;
        }
    }
}
