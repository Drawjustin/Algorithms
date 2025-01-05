import java.util.*;

public class Main {

    static long A, B;
    static long[] dp;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        A = sc.nextLong();
        B = sc.nextLong();

        dp = new long[54];
        dp[1] = 1;
        for (int i = 2; i < 54; i++) {
            dp[i] = (1L << (i - 1)) + dp[i - 1] * 2;
        }

        System.out.println("dp[2] = " + dp[2]);
        System.out.print(countOne(B) - countOne(A-1));
    }

    public static long countOne(long num) {
        if(num == 0) return 0;
        if(num == 1) return 1;
        long len = Long.toBinaryString(num).length();
        long res = 0;
        for (int i = 1; i < len; i++) {
            if((num & (1L << i)) != 0) res += dp[i] + 1 + (num & ((1L << i) - 1));
        }
        return res;
    }
}