package Baekjoon_9527;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long start = sc.nextLong();
        long end = sc.nextLong();

        long [] sum = new long[100];

        for(int i=1; i<=59; i++){
            sum[i] =  ((1L << i-1)+ sum[i-1]*2);
        }
        System.out.println("sum[2] = " + sum[2]);

        System.out.println(cal(sum,end) - cal(sum,start-1));
    }
    public static long cal(long [] sum, long x){
        if(x == 0)
            return 0;
        for(int i=1; ; i++){
            if(x < (1L << i)){
                return (long) (sum[i-1] + x - (1L << i-1) + 1 + cal(sum, (x-(1L << i-1))));
            }
        }
    }
}
