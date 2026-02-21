package 레벨2.연속된부분수열의합;

public class Solution {
    public int[] solution(int[] sequence, int k) {
        int left = 0;
        int total = 0;
        int minLength = Integer.MAX_VALUE;
        int[] result = new int[2];

        for (int right = 0; right < sequence.length; right++) {
            // 1. 오른쪽 포인터를 이동하며 합을 추가
            total += sequence[right];

            // 2. 합이 k보다 크면 왼쪽 포인터를 이동시켜 합을 줄임
            while (total > k) {
                total -= sequence[left++];
            }

            // 3. 합이 k와 일치하는 경우 최단 길이인지 확인
            if (total == k) {
                int currentLength = right - left + 1;
                if (currentLength < minLength) {
                    minLength = currentLength;
                    result[0] = left;
                    result[1] = right;
                }
            }
        }
        return result;
    }
}
