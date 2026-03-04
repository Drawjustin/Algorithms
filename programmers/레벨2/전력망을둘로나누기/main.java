package 레벨2.전력망을둘로나누기;

import java.util.LinkedList;
import java.util.Queue;

public class main {
    class Solution {
        int[][] matrix;

        public int solution(int n, int[][] wires) {
            int answer = Integer.MAX_VALUE;
            matrix = new int[n + 1][n + 1];

            // 1. 인접 행렬 구성 (초기 트리 상태)
            for (int[] wire : wires) {
                matrix[wire[0]][wire[1]] = 1;
                matrix[wire[1]][wire[0]] = 1;
            }

            // 2. 간선 하나씩 끊어보며 완전 탐색
            for (int[] wire : wires) {
                int v1 = wire[0];
                int v2 = wire[1];

                // 전선 끊기 (백트래킹 - 탐색 전 상태 변경)
                matrix[v1][v2] = 0;
                matrix[v2][v1] = 0;

                // 한 쪽 전력망의 송전탑 개수 구하기 (BFS)
                int count = bfs(v1, n);

                // 두 전력망의 노드 수 차이 계산 및 최솟값 갱신
                int diff = Math.abs(count - (n - count));
                answer = Math.min(answer, diff);

                // 전선 다시 연결 (백트래킹 - 원상 복구)
                matrix[v1][v2] = 1;
                matrix[v2][v1] = 1;
            }

            return answer;
        }

        // BFS 탐색: 연결된 송전탑의 개수를 반환
        private int bfs(int startNode, int n) {
            boolean[] visited = new boolean[n + 1];
            Queue<Integer> queue = new LinkedList<>();

            queue.offer(startNode);
            visited[startNode] = true;
            int count = 1; // 시작 노드 포함

            while (!queue.isEmpty()) {
                int current = queue.poll();

                // 1번 노드부터 n번 노드까지 인접 여부 확인
                for (int i = 1; i <= n; i++) {
                    // 연결되어 있고, 아직 방문하지 않은 노드라면 Queue에 추가
                    if (matrix[current][i] == 1 && !visited[i]) {
                        visited[i] = true;
                        queue.offer(i);
                        count++;
                    }
                }
            }

            return count;
        }
    }
}
