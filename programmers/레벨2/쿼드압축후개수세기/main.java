package 레벨2.쿼드압축후개수세기;

public class main {
    class Solution {
        int zero = 0;
        int one = 0;
        public int[] solution(int[][] arr) {
            int[] answer = new int[2];
            divide(arr,0,0, arr.length, arr.length);
            answer[0] = zero;
            answer[1] = one;
            return answer;
        }

        public void divide(int [][] arr, int startY, int startX, int endY, int endX){
            int st = arr[startY][startX];
            for (int i = startY; i < endY; i++) {
                for (int j = startX; j < endX ; j++) {
                    if(st != arr[i][j]){
                        int midY = (startY + endY) / 2;
                        int midX = (startX + endX) / 2;
                        divide(arr, startY, startX, midY, midX);
                        divide(arr, startY, midX, midY, endX);
                        divide(arr, midY, startX, endY, midX);
                        divide(arr, midY, midX, endY, endX);
                        return;
                    }
                }
            }
            if (st == 1) {
                one++;
            } else {
                zero++;
            }
        }
    }
}
