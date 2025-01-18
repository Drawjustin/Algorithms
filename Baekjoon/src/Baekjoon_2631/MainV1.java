package Baekjoon_2631;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * LIS를 이용하는 문제
 * 1. DP(V1)
 * 2. 이분탐색(V2)
 * N의 크기가 200이므로, 최대 N**2의 시간복잡도를 가지므로, 40000번의 연산 -> DP로 해결가능
 */
public class MainV1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int LEN = Integer.parseInt(br.readLine());
        int [] ary = new int [LEN];
        for (int i = 0; i < LEN; i++) {
            ary[i] = Integer.parseInt(br.readLine());
        }
        int [] dp = new int [201];
        int maxDp = -1;
        for (int i = 0; i < LEN; i++) {
            dp[i] = 1;            //해당 원소에서 끝나는 LIS 길이의 최솟값. 즉, 자기 자신
            for (int j = 0; j < i; j++) {
                //i번째 이전의 모든 원소에 대해, 그 원소에서 끝나는 LIS의 길이를 확인한다.
                if (ary[i] > ary[j]) {
                    //단, 이는 현재 수가 그 원소보다 클 때만 확인한다.
                    dp[i] = Math.max(dp[i], dp[j] + 1);        //dp[j] + 1 : 이전 원소에서 끝나는 LIS에 현재 수를 붙인 새 LIS 길이
                    maxDp = Math.max(dp[i],maxDp);
                }
            }
        }
        System.out.println(LEN - maxDp);

    }
}
