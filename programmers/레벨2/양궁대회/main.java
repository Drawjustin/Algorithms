package 레벨2.양궁대회;

import java.util.Arrays;

public class main {
    class Solution {
        int maxDiff = 0;      // 라이언과 어피치의 최대 점수 차이
        int[] bestArr = {-1}; // 정답을 담을 배열

        public int[] solution(int n, int[] info) {
            int[] ryan = new int[11]; // 라이언이 쏜 화살 배열

            // 탐색 시작: 0번째 인덱스(10점 과녁)부터, 화살 n개를 들고 탐색
            dfs(0, n, info, ryan);

            return bestArr;
        }

        // idx: 현재 과녁 인덱스 (0~10)
        // arrows: 남은 화살 개수
        public void dfs(int idx, int arrows, int[] info, int[] ryan) {
            // 1. 종료 조건: 모든 과녁(10점~0점)을 다 돌았을 때
            if (idx == 11) {
                // 남은 화살이 있다면 전부 0점 과녁(인덱스 10)에 털어 넣기
                if (arrows > 0) {
                    ryan[10] += arrows;
                }

                // 여기서 점수 차이를 계산하고 최고 기록을 갱신합니다. (checkScore 함수 필요)
                checkScore(info, ryan);

                // 0점에 넣었던 화살 원상복구 (백트래킹의 핵심!)
                if (arrows > 0) {
                    ryan[10] -= arrows;
                }
                return;
            }

            // 2. 라이언이 이 과녁을 "먹는" 경우
            // 어피치보다 화살을 1개 더 쏠 수 있을 만큼 남은 화살이 충분한지 확인
            if (arrows > info[idx]) {
                ryan[idx] = info[idx] + 1; // 화살 쏘기
                dfs(idx + 1, arrows - ryan[idx], info, ryan); // 다음 과녁으로 이동
                ryan[idx] = 0; // 탐색 끝나면 화살 다시 뽑기 (원상복구)
            }

            // 3. 라이언이 이 과녁을 "포기하는" 경우
            // 화살 안 쏘고 그대로 다음 과녁으로 넘어감
            ryan[idx] = 0;
            dfs(idx + 1, arrows, info, ryan);
        }

        // 점수 계산 및 최고 기록 갱신 로직 (직접 구현해 볼 부분!)
        public void checkScore(int[] info, int[] ryan) {
            // TODO: 라이언과 어피치의 점수를 비교하고,
            // maxDiff보다 크면 bestArr을 갱신하는 로직 작성
            int maxRyan = 0;
            int maxApache = 0;

            for (int i = 0; i < 10; i++) {
                if(info[i] < ryan[i]){
                    maxRyan += 10 - i;
                }else if (info[i] > 0) {
                    // 라이언이 지거나 비겼는데, 어피치가 1발 이상 쏜 경우만 어피치 점수 획득
                    maxApache += 10 - i;
                }
            }
            int diff = maxRyan - maxApache;

            if (diff <= 0) {
                return;
            }

            // 점수 차이가 기존 기록보다 더 큰 경우 (무조건 갱신)
            if (diff > maxDiff) {
                maxDiff = diff;
                bestArr = Arrays.copyOf(ryan, ryan.length);
            }

            // 점수 차이가 기존 기록과 똑같은 경우 (낮은 점수부터 비교)
            else if (diff == maxDiff) {
                // 10번 인덱스(0점)부터 0번 인덱스(10점) 방향으로 거꾸로 확인
                for (int i = 10; i >= 0; i--) {
                    if (ryan[i] > bestArr[i]) {
                        // 지금 구한 방법이 낮은 점수를 더 많이 맞혔다면 갱신!
                        bestArr = Arrays.copyOf(ryan, ryan.length);
                        break;
                    } else if (ryan[i] < bestArr[i]) {
                        // 기존 방법이 낮은 점수를 더 많이 맞혔다면 갱신 안 함!
                        break;
                    }
                    // 맞힌 개수가 같으면 그 다음으로 낮은 점수(i--)를 계속 비교함
                }
            }
        }
    }
}
