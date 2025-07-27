package Baekjoon_22944;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main{
    static int N,H,D;
    static char [][] map;
    static int [][] check;  // 수정: boolean -> int 배열로 변경
    static int[] start = new int[5];
    static int [] dy = {-1,0,1,0};
    static int [] dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        init();
        int result = bfs();
        System.out.println(result);
    }

    private static int bfs() {
        int result = -1;
        Deque<int []> deque = new ArrayDeque<>();
        deque.add(start);
        check[start[0]][start[1]] = start[3] + start[4];  // 수정: 초기 상태 저장

        while(!deque.isEmpty()){
            int [] curValue = deque.poll();

            if(map[curValue[0]][curValue[1]] == 'E'){
                result = curValue[2];
                break;
            }

            if(curValue[3] <= 0){
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nxtY = curValue[0] + dy[i];
                int nxtX = curValue[1] + dx[i];
                int nxtU = curValue[4];
                int nxtH = curValue[3];

                if(!isMoved(nxtY, nxtX))
                    continue;

                // E에 도달했으면 result 갱신
                if(map[nxtY][nxtX] == 'E') {
                    result = result == -1 ? curValue[2] + 1 : Math.min(result, curValue[2] + 1);  // 수정: 최솟값 갱신
                    continue;
                }

                // 우산을 집는 경우
                if(map[nxtY][nxtX] == 'U'){
                    nxtU = D;
                }

                // 비를 맞는 처리 (시작지점이 아닌 경우)
                if(map[nxtY][nxtX] != 'S') {
                    if(nxtU > 0){
                        nxtU--; // 우산 내구도 감소
                    } else {
                        nxtH--; // 체력 감소
                    }
                }

                // 체력이 0 이하가 되면 이동 불가
                if(nxtH <= 0){
                    continue;
                }

                // 수정: 현재 상태가 이전 방문보다 더 진행할 수 있다면 방문 처리
                if(check[nxtY][nxtX] < nxtH + nxtU) {
                    check[nxtY][nxtX] = nxtH + nxtU;
                    deque.add(new int []{nxtY, nxtX, curValue[2]+1, nxtH, nxtU});
                }
            }
        }
        return result;
    }

    private static boolean isMoved(int y, int x){
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new  StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        H = Integer.parseInt(stk.nextToken());
        D = Integer.parseInt(stk.nextToken());

        check = new int[N][N];  // 수정: int 배열로 변경
        map =  new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] =  line.charAt(j);
                if(map[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                    start[2] = 0;
                    start[3] = H;
                    start[4] = 0;
                }
            }
        }
    }
}