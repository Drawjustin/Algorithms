package 레벨2.소수찾기;

import java.util.HashSet;
import java.util.Set;

public class main {
    class Solution {
        public int solution(String numbers) {
            Set<Integer> set = new HashSet<>();

            for (int pick = 1; pick <= numbers.length(); pick++) {
                int[] combAry = new int[numbers.length()];

                // 뒤에서부터 pick개를 1로 채움
                for (int i = numbers.length() - pick; i < numbers.length(); i++) {
                    combAry[i] = 1;
                }

                do {
                    int[] pickedIndex = new int[pick];
                    int idx = 0;

                    // combAry에서 1인 위치의 인덱스만 뽑기
                    for (int i = 0; i < numbers.length(); i++) {
                        if (combAry[i] == 1) {
                            pickedIndex[idx++] = i;
                        }
                    }

                    // 뽑힌 인덱스들의 순열
                    do {
                        int num = 0;
                        for (int i = 0; i < pick; i++) {
                            num = num * 10 + (numbers.charAt(pickedIndex[i]) - '0');
                        }
                        set.add(num);
                    } while (nextPermutation(pickedIndex));

                } while (nextPermutation(combAry));
            }

            int answer = 0;
            for (int num : set) {
                if (isPrime(num)) {
                    answer++;
                }
            }

            return answer;
        }

        public boolean nextPermutation(int[] arr) {
            int i = arr.length - 1;
            while (i > 0 && arr[i - 1] >= arr[i]) i--;

            if (i == 0) return false;

            int j = arr.length - 1;
            while (arr[i - 1] >= arr[j]) j--;

            swap(arr, i - 1, j);

            int k = arr.length - 1;
            while (i < k) swap(arr, i++, k--);

            return true;
        }

        public void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        public boolean isPrime(int n) {
            if (n < 2) return false;

            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) return false;
            }
            return true;
        }
    }
}

