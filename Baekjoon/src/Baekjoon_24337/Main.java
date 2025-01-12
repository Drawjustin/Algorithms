package Baekjoon_24337;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N,A,B;
    public static void main(String[] args) throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        A = Integer.parseInt(stk.nextToken());
        B = Integer.parseInt(stk.nextToken());

        if(N+2 <= A+B){
            System.out.println(-1);
            return;
        }

        ArrayList<Integer> building = new ArrayList<>();
        for(int i = 1; i < A; i++){
            building.add(i);
        }
        building.add(Math.max(A, B));
        for(int i = B - 1; i > 0; i--){
            building.add(i);
        }


        int first = building.remove(0);
        int size = building.size();
        for(int i = 0; i < N - size - 1; i++){
            building.add(0, 1);
        }
        building.add(0, first);
        for(int i = 0; i < N; i++){
            System.out.print(building.get(i)+" ");
        }

    }
}
