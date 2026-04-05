package Baekjoon_11501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int T =  Integer.parseInt(stk.nextToken());
        for (int i = 0; i < T; i++) {
            long result = 0L;
            int N = Integer.parseInt(br.readLine());
            stk = new StringTokenizer(br.readLine());
            List<Integer> list = new ArrayList<>();
            int pocketKing = -1;
            while(stk.hasMoreTokens()){
                list.add(Integer.parseInt(stk.nextToken()));
            }
            for (int j = list.size() -1; j >= 0; j--) {
                if(pocketKing < list.get(j)){
                    pocketKing = list.get(j);
                }
                else{
                    result += pocketKing - list.get(j);
                }
            }

            System.out.println(result);

        }
    }
}
