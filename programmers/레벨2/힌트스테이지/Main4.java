package 레벨2.힌트스테이지;

import java.util.*;

public class Main4 {
    class Solution {
        static int n;
        static Map<State, Integer> memo;
        static int [][] Cost;
        static int [][] Hint;
        static List<Integer>[] hintBundle;

        public int solution(int[][] cost, int[][] hint) {
            n = cost.length;
            memo = new HashMap<>();
            Cost = cost;
            Hint = hint;
            hintBundle = new ArrayList[n];
            for (int i = 1; i < n ; i++) {
                hintBundle[i] = new ArrayList<>();
                int[] ints = Arrays.copyOfRange(hint[i-1], 1, hint[i-1].length);
                for(int h : ints){
                    hintBundle[i].add(h);
                }

            }
            int [] shints = new int[n];
            State sState = new State(1, shints);
            return dfs(sState);
        }
        public int dfs(State state){
            if(state.stage > n)
                return 0;

            Integer memoValue = memo.get(state);
            if(memoValue != null)
                return memoValue;

            State curState = new State(state.stage,state.hints);


            //힌트를 구매하지 않은 세계선

            int curValue = Cost[state.stage-1][state.hints[state.stage-1]];
            curState.hints[state.stage-1] = 0;
            curState.stage++;
            int totalValue = curValue + dfs(curState);

            //힌트를 구매한 세계선
            if(state.stage < n){
                State buyState = new State(state.stage,state.hints);
                for(int q : hintBundle[state.stage]){
                    buyState.hints[q - 1] = Math.min (buyState.hints[q-1] + 1 , n -1);
                }
                buyState.hints[state.stage-1] = 0;
                buyState.stage++;

                int buyValue =
                        curValue
                                + Hint[state.stage - 1][0]
                                + dfs(buyState);

                totalValue = Math.min(totalValue , buyValue);

            }
            memo.put(state, totalValue);
            return totalValue;
        }

        class State {
            int stage;
            int [] hints;
            State(int stage, int [] hints){
                this.stage = stage;
                this.hints = hints.clone();
            }

            @Override
            public boolean equals(Object o){
                if(o == this) return true;
                if(!(o instanceof State)) return false;
                State other = (State) o;
                return other.stage == this.stage && Arrays.equals(other.hints, this.hints);
            }

            @Override
            public int hashCode(){
                return 31 * Integer.hashCode(stage) + Arrays.hashCode(hints);
            }
        }
    }
}
