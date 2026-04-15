package 레벨2.바이러스파이프;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    List<int[]>[] graph;
    int n;
    int start;
    int maxValue;

    public int solution(int n, int infection, int[][] edges, int k) {
        this.n = n;
        this.start = infection;
        this.maxValue = 0;

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

        int[] order = new int[k];
        comb(0, k, order);

        return maxValue;
    }

    void comb(int depth, int k, int[] order) {
        if (maxValue == n) {
            return;
        }

        if (depth == k) {
            maxValue = Math.max(maxValue, simulate(order));
            return;
        }

        for (int pipeType = 1; pipeType <= 3; pipeType++) {
            order[depth] = pipeType;
            comb(depth + 1, k, order);
        }
    }

    int simulate(int[] order) {
        boolean[] infected = new boolean[n + 1];
        infected[start] = true;

        for (int pipeType : order) {
            spread(pipeType, infected);
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (infected[i]) {
                count++;
            }
        }
        return count;
    }

    void spread(int pipeType, boolean[] infected) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (infected[i]) {
                queue.offer(i);
                visited[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int[] edge : graph[current]) {
                int next = edge[0];
                int type = edge[1];

                if (type != pipeType) {
                    continue;
                }
                if (visited[next]) {
                    continue;
                }

                visited[next] = true;
                infected[next] = true;
                queue.offer(next);
            }
        }
    }
}
