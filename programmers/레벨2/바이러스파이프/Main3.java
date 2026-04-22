package 레벨2.바이러스파이프;

import java.util.*;

public class Main3 {
    public class Solution {
        static int n;
        static int start;
        static List<int []>[] graph;
        static Map<State,Integer> memo;
        static int k;
        public int solution(int n, int infection, int[][] edges, int k) {
            this.n = n;
            this.k = k;
            start = infection;
            memo = new HashMap<>();
            graph = new ArrayList[n+1];
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < edges.length; i++) {
                int s = edges[i][0];
                int e = edges[i][1];
                int t = edges[i][2];

                graph[s].add(new int []{e,t});
                graph[e].add(new int []{s,t});
            }

            BitSet infected = new BitSet();
            infected.set(infection);

            return dfs(0, infected);
        }
        private int dfs(int depth, BitSet infected){
            int infectedCount = infected.cardinality();

            if(infectedCount == n || depth == k)
                return infectedCount;

            State state = new State(depth,infected);
            Integer cache = memo.get(state);
            if(cache != null){
                return cache;
            }

            int bestResult = infectedCount;
            for (int i = 1; i <= 3; i++) {
                BitSet nextState = spread(i,infected);
                bestResult = Math.max(bestResult, dfs(depth + 1, nextState));

                if (bestResult == n) {
                    break;
                }
            }
            memo.put(state,bestResult);
            return bestResult;

        }
        public static BitSet spread(int pipeType, BitSet infecting){
            BitSet infected = (BitSet) infecting.clone();

            Deque<Integer> deque = new ArrayDeque<>();
            boolean [] isVisited = new boolean[n+1];

            for (int i = infected.nextSetBit(1); i != -1; i = infected.nextSetBit(i+1)){
                deque.offer(i);
                isVisited[i] = true;
            }

            while(!deque.isEmpty()){
                int curNode = deque.poll();

                for(int[] i: graph[curNode]){
                    int target = i[0];
                    int targetTypeType = i[1];
                    if(isVisited[target] || pipeType != targetTypeType)
                        continue;
                    deque.offer(target);
                    isVisited[target] = true;
                    infected.set(target);
                }
            }
            return infected;


        }
        class State {
            int depth;
            BitSet infected;
            State(int depth, BitSet infected){
                this.depth = depth;
                this.infected = (BitSet) infected.clone();
            }
            @Override
            public boolean equals(Object o){
                if(this == o)return true;
                if(!(o instanceof State)) return false;
                State other = (State) o;
                return other.infected.equals(this.infected)  && this.depth == other.depth;
            }
            @Override
            public int hashCode(){
                return 31 * Integer.hashCode(depth) + infected.hashCode();
            }
        }
    }
}
