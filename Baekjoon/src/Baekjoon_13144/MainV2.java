package Baekjoon_13144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainV2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }

        boolean[] used = new boolean[100001];  // 숫자 사용 여부 체크
        long result = 0;
        int start = 0;

        for (int end = 0; end < N; end++) {
            while (used[arr[end]]) {  // 중복된 수를 만나면
                used[arr[start]] = false;  // 시작점의 숫자를 제거
                start++;
            }
            used[arr[end]] = true;  // 현재 숫자 사용 체크
            result += (end - start + 1);  // 현재 가능한 부분 수열의 개수 추가
        }

        System.out.println(result);
    }
}
