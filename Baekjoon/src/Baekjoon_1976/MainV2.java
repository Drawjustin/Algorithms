package Baekjoon_1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainV2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int [] parent;
    static int N,M;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                boolean canGo = Integer.parseInt(stk.nextToken()) == 1;
                if(canGo && find(j) != find(i)){
                    union(i,j);
                }
            }
        }
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(stk.nextToken());
        start = find(start);
        while(stk.hasMoreTokens()){
            int temp = find(Integer.parseInt(stk.nextToken()));
            if(temp != start){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    static boolean union(int a, int b){
        a = find(a);
        b = find(b);
        if(a == b) return false;
        if(a <= b) parent[b] = a;
        else parent[a] = b;
        return true;
    }
    static int find(int a){
        if(parent[a] == a) return a;
        return find(parent[a]);
    }
}
