package Baekjoon_1027;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static List<int []> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int result = 0;
        for (int i = 0; i < N; i++) {
            list.add(new int [] {i+1,Integer.parseInt(stk.nextToken())});
        }


        for (int i = 0; i < N; i++) {
            int [] start = list.get(i);

            int left = leftCal(start, i);
            int right = rightCal(start, i);
            result = Math.max(result,left+right);
        }
        System.out.println(result);
    }

    private static int rightCal(int[] start, int i) {
        int right = 0;
        double minGradient = Double.NEGATIVE_INFINITY;
        for (int j = i + 1; j < N; j++) {
            int[] target = list.get(j);
            double tempInclination = (double)(target[1]-start[1])/(target[0]-start[0]);
            if(tempInclination>minGradient){
                minGradient = tempInclination;
                right++;
            }
        }
        return right;
    }

    private static int leftCal(int[] start, int i) {
        int left = 0;
        double minGradient = Double.NEGATIVE_INFINITY;

        for (int j = i-1; j >=0; j--) {
            int[] target = list.get(j);
            double tempInclination = (double)(target[1]-start[1])/(start[0]-target[0]);
            if(tempInclination>minGradient){
                minGradient = tempInclination;
                left++;
            }
        }
        return left;
    }

}
