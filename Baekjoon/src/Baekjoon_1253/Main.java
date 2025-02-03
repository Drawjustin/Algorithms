package Baekjoon_1253;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        int result = 0;
        String[] s = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(s[i]));
        }

        Collections.sort(list);

        for (int i = 0; i < N; i++) {
            if(logic(list.get(i), i)) {
                result++;
            }
        }
        System.out.println(result);
    }

    private static boolean logic(int target, int targetIndex) {
        int left = 0;
        int right = N - 1;

        while (left < right) {

            if (left == targetIndex) {
                left++;
                continue;
            }
            if (right == targetIndex) {
                right--;
                continue;
            }

            int sum = list.get(left) + list.get(right);

            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
}