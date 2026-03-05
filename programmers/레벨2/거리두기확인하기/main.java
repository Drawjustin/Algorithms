package 레벨2.거리두기확인하기;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class main {
    class Solution {
        static int [] dy = {0,1,0,-1};
        static int [] dx = {1,0,-1,0};
        public int[] solution(String[][] places) {
            int[] answer = new int [places.length];

            Arrays.fill(answer, 1);

            for (int q = 0; q < places.length; q++) {
                boolean qFlag = true;
                boolean[][] visited = new boolean[places[0].length][places[0][0].length()];
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if(places[q][i].charAt(j) =='P' && !visited[i][j] && qFlag){
                            boolean flag = bfs(i,j,visited,places[q]);
                            if(!flag){
                                answer[q] = 0;
                                qFlag = false;
                                break;
                            }
                        }
                    }
                }
            }


            return answer;
        }
        public boolean bfs(int startY, int startX, boolean[][] visited, String[] places) {
            Deque<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{startY, startX, 0});
            visited[startY][startX] = true;

            while(!queue.isEmpty()){
                int [] node = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nxtY = node[0] + dy[i];
                    int nxtX = node[1] + dx[i];

                    if(nxtY < 0 || nxtY >= 5 || nxtX < 0 || nxtX >= 5) continue;
                    if(places[nxtY].charAt(nxtX) == 'X') continue;
                    if(visited[nxtY][nxtX]) continue;

                    // 1. 사람('P')을 발견하면 거리가 1이든 2든 무조건 위반! (즉시 0점 처리)
                    if(places[nxtY].charAt(nxtX) == 'P'){
                        return false;
                    }

                    // 2. 빈 테이블('O')을 만났을 때, 큐에 넣을지 말지 여기서 결정!
                    // 현재 거리가 0일 때만 (즉, 이 'O'가 거리 1일 때만) 큐에 넣습니다.
                    if(node[2] == 0) {
                        visited[nxtY][nxtX] = true;
                        queue.offer(new int[]{nxtY, nxtX, node[2] + 1});
                    }
                }
            }
            return true;
        }
    }
}
