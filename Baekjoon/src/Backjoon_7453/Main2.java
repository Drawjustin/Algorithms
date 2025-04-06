package Backjoon_7453;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    static int n;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long[][] array;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        array = new long[n][4];

        for (int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                array[i][j] = Long.parseLong(stk.nextToken());
            }
        }

        // A+B 조합을 모두 계산하여 배열에 저장
        long[] sumAB = new long[n * n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumAB[index++] = array[i][0] + array[j][1];
            }
        }

        // C+D 조합을 모두 계산하여 배열에 저장
        long[] sumCD = new long[n * n];
        index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumCD[index++] = array[i][2] + array[j][3];
            }
        }

        // 두 배열 정렬
        Arrays.sort(sumAB);
        Arrays.sort(sumCD);

        // 이분 검색을 통해 결과 계산
        long result = 0;

        // 투 포인터 기법
        int left = 0;
        int right = sumCD.length - 1;

        while (left < sumAB.length && right >= 0) {
            long sum = sumAB[left] + sumCD[right];

            if (sum == 0) {
                // 같은 값이 여러 개 있을 수 있으므로 개수를 계산
                long countAB = 1;
                long countCD = 1;

                // 같은 A+B 값의 개수 계산
                while (left + 1 < sumAB.length && sumAB[left] == sumAB[left + 1]) {
                    countAB++;
                    left++;
                }

                // 같은 C+D 값의 개수 계산
                while (right - 1 >= 0 && sumCD[right] == sumCD[right - 1]) {
                    countCD++;
                    right--;
                }

                // 총 조합 수 계산
                result += countAB * countCD;

                left++;
                right--;
            } else if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(result);
    }
}