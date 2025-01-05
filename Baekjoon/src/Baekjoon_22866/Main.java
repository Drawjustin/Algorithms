package Baekjoon_22866;

import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<List<int []>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        /**
         * 일직선으로 다양한 높이의 건물이 총
         * N개가 존재한다. 각 건물 옥상에서 양 옆에 존재하는 건물의 옆을 몇 개 볼 수 있는지 궁금해졌다.
         *
         * 번호	1	2	3	4	5	6	7	8
         * 높이	3	7	1	6	3	5	1	7
         * 보이는 건물 번호	2	x	2, 4, 8	2, 8	2,4,6,8	2,4,8	2,4,6,8	x
         *
         * 예제
         * 8
         * 3 7 1 6 3 5 1 7
         * 답
         * 1 2
         * 0
         * 3 2
         * 2 2
         * 4 4
         * 3 4
         * 4 6
         * 0
         *
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[N + 1];
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            heights[i] = Integer.parseInt(stk.nextToken());
        }

        // 좌우에서 보이는 건물 계산
        int[] visibleCount = new int[N + 1];
        int[] nearestBuilding = new int[N + 1];

        // 왼쪽에서 보이는 건물
        Stack<int[]> leftStack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            while (!leftStack.isEmpty() && leftStack.peek()[1] <= heights[i]) {
                leftStack.pop();
            }
            visibleCount[i] += leftStack.size();
            if (!leftStack.isEmpty()) {
                nearestBuilding[i] = leftStack.peek()[0];
            }
            leftStack.push(new int[]{i, heights[i]});
        }

        // 오른쪽에서 보이는 건물
        Stack<int[]> rightStack = new Stack<>();
        for (int i = N; i >= 1; i--) {
            while (!rightStack.isEmpty() && rightStack.peek()[1] <= heights[i]) {
                rightStack.pop();
            }
            visibleCount[i] += rightStack.size();
            if (!rightStack.isEmpty() && (nearestBuilding[i] == 0 ||
                    Math.abs(i - rightStack.peek()[0]) < Math.abs(i - nearestBuilding[i]))) {
                nearestBuilding[i] = rightStack.peek()[0];
            }
            rightStack.push(new int[]{i, heights[i]});
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (visibleCount[i] == 0) {
                sb.append("0\n");
            } else {
                sb.append(visibleCount[i]).append(" ").append(nearestBuilding[i]).append("\n");
            }
        }
        System.out.print(sb);


    }
}
