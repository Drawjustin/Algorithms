package 레벨2.혼자서하는틱택토;

public class main {
    class Solution {
        public int solution(String[] board) {
            int answer = 1;

            // 기존 스타일 유지: char 배열로 변환
            char[][] newBoard = new char[3][3];
            int oCount = 0;
            int xCount = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    newBoard[i][j] = board[i].charAt(j);
                    if (newBoard[i][j] == 'O') oCount++;
                    else if (newBoard[i][j] == 'X') xCount++;
                }
            }

            do {
                // 1. 기본적인 개수 규칙 위반
                if (oCount < xCount || oCount > xCount + 1) {
                    answer = 0;
                    break;
                }

                // 2. O와 X의 승리 여부를 각각 확인 (변수 덮어쓰기 방지)
                boolean oWin = checkWin(newBoard, 'O');
                boolean xWin = checkWin(newBoard, 'X');

                // 3. 둘 다 승리하는 경우 (불가능)
                if (oWin && xWin) {
                    answer = 0;
                    break;
                }

                // 4. O가 승리했을 때: 반드시 oCount == xCount + 1 이어야 함
                if (oWin && oCount != xCount + 1) {
                    answer = 0;
                    break;
                }

                // 5. X가 승리했을 때: 반드시 oCount == xCount 이어야 함
                if (xWin && oCount != xCount) {
                    answer = 0;
                    break;
                }

            } while (false);

            return answer;
        }

        // 기존에 반복문으로 작성하시던 승리 확인 로직을 별도 메서드로 분리 (중복 제거 및 정확성)
        private boolean checkWin(char[][] b, char p) {
            for (int i = 0; i < 3; i++) {
                // 가로 검사
                if (b[i][0] == p && b[i][1] == p && b[i][2] == p) return true;
                // 세로 검사
                if (b[0][i] == p && b[1][i] == p && b[2][i] == p) return true;
            }
            // 대각선 검사
            if (b[0][0] == p && b[1][1] == p && b[2][2] == p) return true;
            if (b[0][2] == p && b[1][1] == p && b[2][0] == p) return true;

            return false;
        }
    }
}
