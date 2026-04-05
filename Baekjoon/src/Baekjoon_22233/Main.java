package Baekjoon_22233;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder stb = new StringBuilder();
        Set<String> set = new HashSet<>();
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine(),",");
            while(stk.hasMoreTokens()){
                String nxt =  stk.nextToken();
                set.remove(nxt);
            }
            stb.append(set.size()).append('\n');
        }
        System.out.print(stb);
    }
}
