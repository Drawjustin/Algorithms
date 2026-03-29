package 레벨2.의상;

import java.util.HashMap;
import java.util.Map;

public class main {
    class Solution {
        public int solution(String[][] clothes) {
            int answer = 0;

            Map<String, Integer> stringClothes = new HashMap<>();
            for (int i = 0; i < clothes.length; i++) {
                String[] cloth = clothes[i];
                stringClothes.put(cloth[1], stringClothes.getOrDefault(cloth[1], 0) + 1);
            }

            int[] clothType = new int[stringClothes.size()];

            int idx = 0;
            for (int i : stringClothes.values()) {
                clothType[idx++] = i;
            }

            for (int i = 1; i <= clothType.length; i++) {
                int[] indexAry = new int[clothType.length];
                for (int j = clothType.length - 1; j >= clothType.length - i; j--) {
                    indexAry[j] = 1;
                }

                do {
                    int result = 1;
                    for (int j = 0; j < indexAry.length; j++) {
                        if (indexAry[j] == 1) {
                            result *= clothType[j];
                        }
                    }
                    answer += result;
                } while (nextPermutation(indexAry));
            }

            return answer;
        }

        public boolean nextPermutation(int[] array) {
            int i = array.length - 1;
            while (i > 0 && array[i - 1] >= array[i]) i--;
            if (i == 0) return false;

            int j = array.length - 1;
            while (array[i - 1] >= array[j]) j--;

            swap(array, i - 1, j);

            int k = array.length - 1;
            while (i < k) swap(array, i++, k--);

            return true;
        }

        public void swap(int[] ary, int i, int j) {
            int temp = ary[i];
            ary[i] = ary[j];
            ary[j] = temp;
        }
    }
}
