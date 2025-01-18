package Baekjoon_2631;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * LIS를 이용하는 문제
 * 1. DP(V1)
 * 2. 이분탐색(V2)
 * N의 크기가 200이므로, 최대 N**2의 시간복잡도를 가지므로, 40000번의 연산 -> DP로 해결가능
 */
public class MainV2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        int LEN = Integer.parseInt(br.readLine());

        for (int i = 0; i < LEN; i++) {
            int target = Integer.parseInt(br.readLine());
            if(!list.isEmpty() && list.get(list.size()-1) >= target){
                int idx = lower_bound(list,target);
                list.set(idx,target);
            }else{
                list.add(target);
            }
        }

        System.out.println(LEN - list.size());

    }

    static int lower_bound(List<Integer> list, int target){
        int begin = 0;
        int end = list.size();

        while(begin < end) {
            int mid = (begin + end) / 2;

            if(list.get(mid) >= target) {
                end = mid;
            }
            else {
                begin = mid + 1;
            }
        }
        return end;
    }
    static int upper_bound(List<Integer> list, int target){
        int begin = 0;
        int end = list.size();

        while(begin < end) {
            int mid = (begin + end) / 2;

            if(list.get(mid) <= target) {
                begin = mid+1;
            }
            else {
                end = mid;
            }
        }
        return end;
    }


}
