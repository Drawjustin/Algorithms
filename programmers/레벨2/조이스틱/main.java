package 레벨2.조이스틱;

public class main {
    class Solution {
        public int solution(String name) {
            int answer = 0;
            int len = name.length();

            // 커서 좌우 이동의 최댓값 (오른쪽으로만 쭉 가는 경우)
            int minMove = len - 1;

            for (int i = 0; i < len; i++) {
                // 1. 상하 조작 횟수 계산 (위로 가는 것과 아래로 가는 것 중 최솟값)
                char c = name.charAt(i);
                answer += Math.min(c - 'A', 'Z' - c + 1);

                // 2. 좌우 커서 이동 횟수 계산
                // 현재 위치(i) 다음부터 연속된 'A'가 끝나는 지점(next) 찾기
                int next = i + 1;
                while (next < len && name.charAt(next) == 'A') {
                    next++;
                }

                // 패턴 1: 원점에서 오른쪽으로 i만큼 왔다가, 다시 왼쪽으로 돌아가서 끝에서부터 탐색하는 경우
                int moveRightThenLeft = (i * 2) + (len - next);

                // 패턴 2: 원점에서 왼쪽으로 먼저 가서 끝부분을 탐색하고, 다시 오른쪽으로 돌아와 i까지 가는 경우
                int moveLeftThenRight = (len - next) * 2 + i;

                // 기존의 최소 이동 횟수와 새로운 패턴 1, 패턴 2 중 가장 작은 값을 선택
                minMove = Math.min(minMove, Math.min(moveRightThenLeft, moveLeftThenRight));
            }

            // 전체 알파벳 조작 횟수 + 최소 커서 이동 횟수
            answer += minMove;
            return answer;
        }
    }

//    class Solution {
//        public int solution(String name) {
//            int answer = 0;
//
//            int cur = 1;
//            answer = Math.min(Math.abs('A' - name.charAt(0)),Math.abs('Z'-name.charAt(0)-1));
//
//            int curIndex = 0;
//            while(cur < answer) {
//
//                int curRight = curIndex;
//                int rightCount = 0;
//                int curLeft = curIndex;
//                int leftCount = 0;
//                cur++;
//                do {
//                    leftCount++;
//                    curLeft =  (curLeft + name.length() - 1) % name.length();
//
//                }while(name.charAt(curLeft) == 'A');
//                do {
//                    rightCount++;
//                    curRight =  (curLeft + name.length() - 1) % name.length();
//                }while(name.charAt(curRight) == 'A');
//
//                if(curLeft >= curRight){
//                    answer += rightCount;
//                    curIndex = curRight;
//                }else{
//                    answer += leftCount;
//                    curIndex = curLeft;
//                }
//            }
//
//
//            return answer;
//        }
//    }
}
