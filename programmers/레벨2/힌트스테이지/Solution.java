package 레벨2.힌트스테이지;

import java.util.*;

class Solution {
    PriorityQueue<Integer> result = new PriorityQueue<>();
    public int solution(int[][] cost, int[][] hint) {
        dfs(1,cost.length,cost,hint,new State(0, new int[cost.length + 1]));
        int answer = result.peek();
        return answer;
    }
    void dfs(int curStage, int maxStage, int [][] cost, int [][] hint, State state){
        if(curStage > maxStage){
            result.add(state.totalPrice);
            return;
        }
        int usableHints = Math.min(state.hints[curStage], cost[curStage - 1].length - 1);
        int curPrice = state.totalPrice + cost[curStage - 1][usableHints];
        if(curStage < maxStage) {
            int curStageHintPrice = hint[curStage - 1][0];
            int[] curStageHintList = Arrays.copyOfRange(hint[curStage - 1], 1, hint[curStage - 1].length);
            int[] buyHints = state.hints.clone();
            for (int i : curStageHintList) {
                buyHints[i]++;
            }

            State buyHintStage = new State(curPrice + curStageHintPrice, buyHints);
            dfs(curStage+1, maxStage, cost,hint,buyHintStage);
        }
        State notBuyHintStage = new State(curPrice, state.hints);
        dfs(curStage+1,maxStage,cost,hint,notBuyHintStage);
    }

    class State {
        // n번째 힌트의 개수는 n번째 배열의 값
        int [] hints;
        int totalPrice;
        State(int totalPrice, int [] hints){
            this.totalPrice = totalPrice;
            this.hints = hints.clone();
        }
    }
}