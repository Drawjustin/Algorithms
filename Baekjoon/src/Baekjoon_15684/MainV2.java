package Baekjoon_15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainV2 {
    static int N, M, H;
    static int[][] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new int[H+1][N+1];

        // 주어진 가로선 입력
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = 1; // 오른쪽으로 이동
        }

        // 0개부터 3개까지 시도
        int answer = -1;
        for(int i = 0; i <= 3; i++) {
            if(dfs(0, i, 1, 1)) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }

    // count: 현재까지 놓은 가로선 수
    // target: 목표 가로선 수
    // row: 현재 검사할 행
    // col: 현재 검사할 열
    static boolean dfs(int count, int target, int row, int col) {
        if(count == target) {
            return check();
        }

        for(int i = row; i <= H; i++) {
            for(int j = (i == row ? col : 1); j < N; j++) {
                // 가로선을 놓을 수 있는지 확인
                if(ladder[i][j] == 0 && ladder[i][j-1] != 1 &&
                        (j == N-1 || ladder[i][j+1] != 1)) {
                    ladder[i][j] = 1;
                    if(dfs(count + 1, target, i, j + 2)) return true;
                    ladder[i][j] = 0;
                }
            }
        }
        return false;
    }

    // 모든 세로선이 자기 번호로 도착하는지 확인
    static boolean check() {
        for(int start = 1; start <= N; start++) {
            int col = start;
            for(int row = 1; row <= H; row++) {
                if(ladder[row][col-1] == 1) {
                    col--;
                } else if(col < N && ladder[row][col] == 1) {
                    col++;
                }
            }
            if(col != start) return false;
        }
        return true;
    }
}