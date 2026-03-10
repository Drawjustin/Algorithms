package 레벨2.쿼드압축후개수세기;

public class main {
    class Solution {
        public int[] solution(int[][] arr) {
            return divide(arr, 0, 0, arr.length);
        }

        private int[] divide(int[][] arr, int y, int x, int size) {
            int first = arr[y][x];

            for (int i = y; i < y + size; i++) {
                for (int j = x; j < x + size; j++) {
                    if (arr[i][j] != first) {
                        int half = size / 2;

                        int[] a = divide(arr, y, x, half);
                        int[] b = divide(arr, y, x + half, half);
                        int[] c = divide(arr, y + half, x, half);
                        int[] d = divide(arr, y + half, x + half, half);

                        return new int[] {
                                a[0] + b[0] + c[0] + d[0],
                                a[1] + b[1] + c[1] + d[1]
                        };
                    }
                }
            }

            return first == 0 ? new int[]{1, 0} : new int[]{0, 1};
        }
    }
}
