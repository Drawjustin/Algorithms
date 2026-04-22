package 레벨2.바이러스파이프;

import java.util.*;

public class Main4 {
    public class Solution {
        static int n;
        static int k;
        static Map<State, Integer> memo;
        static List<int []>[] graph;
        public int solution(int n, int infection, int[][] edges, int k) {
            this.n = n;
            this.k = k;
            memo = new HashMap<>();
            BitSet start = new BitSet();
            start.set(infection);

            graph = new ArrayList[n+1];
            for (int i = 0; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < edges.length; i++) {
                int st = edges[i][0];
                int ed = edges[i][1];
                int pipeType = edges[i][2];

                graph[st].add(new int []{ed,pipeType});
                graph[ed].add(new int []{st,pipeType});
            }


            return dfs(0,start);
        }
        public int dfs(int depth, BitSet infecting){
            int infectedCount = infecting.cardinality();

            if(infectedCount == n || depth == k)
                return infectedCount;

            State state = new State(depth, infecting);
            Integer cache = memo.get(state);
            if(cache != null)
                return cache;

            for (int i = 1; i <= 3; i++) {
                BitSet nextInfected = spread(i,infecting);
                infectedCount = Math.max(infectedCount,dfs(depth+1, nextInfected));
                if(infectedCount == n)
                    break;
            }

            memo.put(state,infectedCount);
            return infectedCount;
        }
        public BitSet spread(int pipeType, BitSet infecting){
            BitSet infected = (BitSet) infecting.clone();
            Deque<Integer> deque = new ArrayDeque<>();
            boolean[] isVisited = new boolean[n+1];

            for (int i = infected.nextSetBit(1); i != -1 ; i = infected.nextSetBit(i+1)) {
                deque.offer(i);
                isVisited[i] = true;
            }


            while(!deque.isEmpty()){
                int curNode = deque.poll();

                for(int[] nxtNode : graph[curNode]){
                    if(isVisited[nxtNode[0]] || pipeType != nxtNode[1])
                        continue;

                    infected.set(nxtNode[0]);
                    isVisited[nxtNode[0]] = true;
                    deque.offer(nxtNode[0]);
                }
            }

            return infected;
        }
        public class State{
            int depth;
            BitSet infected;
            public State(int depth, BitSet infected){
                this.depth = depth;
                this.infected = (BitSet) infected.clone();
            }

            @Override
            public boolean equals(Object o){
                if(this == o)return true;
                if(!(o instanceof State)) return false;
                State other = (State) o;
                return depth ==  other.depth && infected.equals(other.infected);
            }

            @Override
            public int hashCode(){
                return 31 * Integer.hashCode(depth) + infected.hashCode();
            }


        }
    }
}
