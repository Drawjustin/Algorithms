package 레벨2.바이러스파이프;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution2 {
    List<int[]>[] graph;
    int n;
    int start;
    int k;
    Map<State, Integer> memo;

    public int solution(int n, int infection, int[][] edges, int k) {
        this.n = n;
        this.start = infection;
        this.k = k;
        this.memo = new HashMap<>();

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int type = edge[2];

            graph[u].add(new int[]{v, type});
            graph[v].add(new int[]{u, type});
        }

        BitSet infected = new BitSet(n + 1);
        infected.set(start);

        return dfs(0, infected);
    }

    int dfs(int depth, BitSet infected) {
        int infectedCount = infected.cardinality();

        if (infectedCount == n || depth == k) {
            return infectedCount;
        }

        State key = new State(depth, infected);
        Integer cached = memo.get(key);
        if (cached != null) {
            return cached;
        }

        int best = infectedCount;

        for (int pipeType = 1; pipeType <= 3; pipeType++) {
            BitSet next = spread(pipeType, infected);

            // 상태가 그대로면 같은 타입 반복 선택은 의미가 적으니 가지치기 가능
            best = Math.max(best, dfs(depth + 1, next));

            if (best == n) {
                break;
            }
        }

        memo.put(key, best);
        return best;
    }

    BitSet spread(int pipeType, BitSet infected) {
        BitSet next = (BitSet) infected.clone();
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        for (int node = infected.nextSetBit(1); node != -1; node = infected.nextSetBit(node + 1)) {
            queue.offer(node);
            visited[node] = true;
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int[] edge : graph[current]) {
                int neighbor = edge[0];
                int type = edge[1];

                if (type != pipeType || visited[neighbor]) {
                    continue;
                }

                visited[neighbor] = true;
                next.set(neighbor);
                queue.offer(neighbor);
            }
        }

        return next;
    }

    static class State {
        int depth;
        BitSet infected;

        State(int depth, BitSet infected) {
            this.depth = depth;
            this.infected = (BitSet) infected.clone();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State)) return false;
            State other = (State) o;
            return depth == other.depth && infected.equals(other.infected);
        }

        @Override
        public int hashCode() {
            int result = Integer.hashCode(depth);
            result = 31 * result + infected.hashCode();
            return result;
        }
    }
}
