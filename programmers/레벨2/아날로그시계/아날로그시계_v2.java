//package 레벨2.아날로그시계;
//
//class Solution {
//    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
//        // 종료 시각까지의 알람 횟수 - 시작 시각까지의 알람 횟수
//        int endCount = getAlarmCount(h2, m2, s2);
//        int startCount = getAlarmCount(h1, m1, s1);
//
//        int answer = endCount - startCount;
//
//        // 시작 시각에 정확히 겹치는 경우 추가
//        if (isAlarmAtTime(h1, m1, s1)) {
//            answer++;
//        }
//
//        return answer;
//    }
//
//    /**
//     * 0시 0분 0초부터 주어진 시각까지 알람이 울린 총 횟수
//     */
//    private int getAlarmCount(int h, int m, int s) {
//        // 12시간 기준으로 변환
//        h = h % 12;
//
//        // 총 초로 변환
//        int totalSeconds = h * 3600 + m * 60 + s;
//
//        // 초침과 시침이 만나는 횟수 계산
//        // 12시간 동안: 초침 720바퀴, 시침 1바퀴 → 719번 만남
//        int hourMeetings = totalSeconds * 719 / 43200;
//
//        // 초침과 분침이 만나는 횟수 계산
//        // 1시간 동안: 초침 60바퀴, 분침 1바퀴 → 59번 만남
//        // 12시간 동안: 59 * 12 = 708번 만남
//        int minuteMeetings = totalSeconds * 59 / 3600;
//
//        int total = hourMeetings + minuteMeetings;
//
//        // 0시 0분 0초(또는 12시 0분 0초)에는 3개 바늘이 모두 겹쳐서
//        // 시침-초침, 분침-초침 두 번 카운트되지만 실제로는 1번만 울림
//        // 따라서 중복 제거
//        if (h == 0 && m == 0 && s == 0) {
//            total -= 1;
//        }
//
//        return total;
//    }
//
//    /**
//     * 특정 시각에 알람이 울리는지 확인
//     */
//    private boolean isAlarmAtTime(int h, int m, int s) {
//        // 각 바늘의 각도 계산 (degree)
//
//        // 초침: 초당 6도
//        double secondAngle = s * 6.0;
//
//        // 분침: 분당 6도 = 초당 0.1도
//        double minuteAngle = (m * 60 + s) * 0.1;
//
//        // 시침: 시간당 30도 = 분당 0.5도 = 초당 1/120도
//        double hourAngle = (h % 12) * 30.0 + m * 0.5 + s * (1.0 / 120.0);
//
//        // 각도 차이 계산 (절댓값)
//        double diffHour = Math.abs(secondAngle - hourAngle);
//        double diffMinute = Math.abs(secondAngle - minuteAngle);
//
//        // 360도 회전 고려 (예: 359도와 1도는 2도 차이)
//        diffHour = Math.min(diffHour, 360 - diffHour);
//        diffMinute = Math.min(diffMinute, 360 - diffMinute);
//
//        // 오차 범위 내에서 겹치는지 확인
//        double epsilon = 0.01;
//
//        return diffHour < epsilon || diffMinute < epsilon;
//    }
//}
