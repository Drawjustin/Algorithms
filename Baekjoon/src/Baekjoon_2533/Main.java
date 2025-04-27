package Baekjoon_2533;

import java.util.*;

public class Main {
    static int n;
    static List<Integer>[] e;
    static int[][] dp; // dp[x][0]: 어댑터일 때, dp[x][1]: 일반인일 때
    static boolean[] visited;

    public static void find(int x) {
        visited[x] = true;
        dp[x][0] = 1; // 어댑터로 선택할 경우

        for (int child : e[x]) {
            if (visited[child]) continue;
            find(child);
            dp[x][1] += dp[child][0];
            dp[x][0] += Math.min(dp[child][0], dp[child][1]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        e = new ArrayList[n + 1];
        dp = new int[n + 1][2];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            e[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            e[u].add(v);
            e[v].add(u);
        }

        find(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
}
