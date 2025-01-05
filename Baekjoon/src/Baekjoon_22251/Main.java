package Baekjoon_22251;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int [][] ary = new int [][]{
            {0,4,3,3,4,3,2,3,1,2},
            {4,0,5,3,2,5,6,1,5,4},
            {3,5,0,2,5,4,3,4,2,3},
            {3,3,2,0,3,2,3,2,2,1},
            {4,2,5,3,0,3,4,3,3,2},
            {3,5,4,2,3,0,1,4,2,1},
            {2,6,3,3,4,1,0,5,1,2},
            {3,1,4,2,3,4,5,0,4,3},
            {1,5,2,2,3,2,1,4,0,1},
            {2,4,3,1,2,1,2,3,1,0}};
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int result = 0;
    private static int N,K,P,X;
    private static boolean [] isvisited;
    public static void main(String[] args) throws IOException {
        /**
         * N K P X
         * 9 1 2 5
         * 1층부터 9(N)층까지 이용가능한 엘리베이터가 있다.
         * 엘리베이터의 층수를 보여주는 1(K)자리수 디스플레이가 있다.
         * 디스플레이의 LED중에서 최소 1개, 최대 2(P)개를 반전시키려고한다.
         * 반전 이후에 디스플레이에 올바른 수가 보여지면서 1이상 9(N)이하가 되도록 한다.
         * 엘리베이터는 실제로 5(X)층에 멈춰있을 때
         * 호식이가 반전시킬 LED를 고를 수 있는 경우의 수

         * 예시에서는 5층 -> 3,6,8,9 가능, 답은 4
         *
         */
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());
        P = Integer.parseInt(stk.nextToken());
        X = Integer.parseInt(stk.nextToken());
        int[] x_digit = num_to_digit(X);
        isvisited = new boolean[100];
        check(x_digit);
        System.out.println(result);
    }
    public static void check(int[] x_digit) {
        for(int i = 1; i <= N; i++) {
            if(i == X) continue;
            if(can_reverse(i, x_digit)) result++;
        }
    }
    public static boolean can_reverse(int target, int[] x_digit) {
        int[] target_digit = num_to_digit(target);
//        int diff_count = 0;
//        for(int i = 0; i < K; i++) {
//            for(int j = 0; j < 7; j++) {
//                if(ary[x_digit[i]][j] != ary[target_digit[i]][j]) {
//                    diff_count++;
//                    if(diff_count > P) return false;
//                }
//            }
//        }

        int left_count = P;
        for(int i=0; i<K; i++){
            if(left_count - ary[x_digit[i]][target_digit[i]] < 0){
                return false;
            }
            left_count -= ary[x_digit[i]][target_digit[i]];
        }
        return true;
    }
    public static int[] num_to_digit(int x) {
        int[] result = new int[K];
        for(int i = K - 1; i >= 0; i--) {
            result[i] = x % 10;
            x /= 10;
        }
        return result;
    }

}
