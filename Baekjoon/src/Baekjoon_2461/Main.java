package Baekjoon_2461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        List<int[]>[] list = new ArrayList[N + 1];

        // 각 반의 능력치 입력 및 정렬
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                list[i].add(new int[]{Integer.parseInt(stk.nextToken()), i});
            }
            Collections.sort(list[i], new Comparator<int[]>() {
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
        }

        // 우선순위 큐를 사용한 해결법
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; // 능력치 기준 오름차순
            }
        });

        // 각 반의 첫 번째(최솟값) 학생들을 큐에 추가
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int[] student = list[i].get(0);
            pq.add(new int[]{student[0], student[1], 0}); // {능력치, 반번호, 해당반에서의인덱스}
            maxVal = Math.max(maxVal, student[0]);
        }

        int minDiff = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int ability = current[0];
            int classNum = current[1];
            int index = current[2];

            // 현재 최댓값과 최솟값의 차이 계산
            minDiff = Math.min(minDiff, maxVal - ability);

            // 해당 반에서 다음 학생이 있다면 큐에 추가
            if (index + 1 < list[classNum].size()) {
                int[] nextStudent = list[classNum].get(index + 1);
                pq.add(new int[]{nextStudent[0], nextStudent[1], index + 1});
                maxVal = Math.max(maxVal, nextStudent[0]);
            } else {
                // 어느 한 반의 모든 학생을 다 확인했으면 종료
                break;
            }
        }

        System.out.println(minDiff);
    }
}