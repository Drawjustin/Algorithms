package Baekjoon_2110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /**
     * 도현이의 집 N개가 수직선 위에 있다. 각각의 집의 좌표는 x1, ..., xN이고, 집 여러개가 같은 좌표를 가지는 일은 없다.
     *
     * 도현이는 언제 어디서나 와이파이를 즐기기 위해서 집에 공유기 C개를 설치하려고 한다. 최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에, 한 집에는 공유기를 하나만 설치할 수 있고, 가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치하려고 한다.
     *
     * C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.
     *
     *
     * 완전탐색 문제, 이분탐색을 통한 가능한 한 최대의 거리 찾기
     *
     *
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,C,result;
    static int [] ary;
    public static void main(String[] args) throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        ary = new int[N];
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(ary);

        binary_search();

        System.out.println(result);

    }

    private static void binary_search() {
        int start = 0;
        int end = ary[N-1]+1;
        while(start < end) {
            int mid = (start + end) / 2;
            if(canDo(mid)){    // 중간 숫자가 가능하면
                result = mid;
                start = mid+1;
            }else{
                end = mid;
            }
        }
    }

    private static boolean canDo(int mid){
        boolean isCan = true;
        int innerIdx = -1;
        int nowDoit = 0;
        for (int i = 0; i < N; i++) {
            if(innerIdx == -1){
                innerIdx = ary[i];
                nowDoit++;
                continue;
            }
            if(ary[i] - innerIdx >= mid){
                innerIdx = ary[i];
                nowDoit++;
            }
            if(nowDoit == C)
                break;
        }
        isCan = nowDoit >= C;
        return isCan;
    }
}
