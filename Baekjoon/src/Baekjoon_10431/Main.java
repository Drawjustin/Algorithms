package Baekjoon_10431;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int testcase = Integer.parseInt(br.readLine());
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < testcase; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int result = 0;
            int n = Integer.parseInt(stk.nextToken());
            List<Integer> list = new ArrayList<>();
            while(stk.hasMoreTokens()){
                int token = Integer.parseInt(stk.nextToken());
                for (int j = 0; j < list.size(); j++) {
                    if(token < list.get(j)){
                        result++;
                    }
                }
                list.add(token);
            }
            stb.append(n).append(" ").append(result).append("\n");
        }
        System.out.print(stb);
    }
}
