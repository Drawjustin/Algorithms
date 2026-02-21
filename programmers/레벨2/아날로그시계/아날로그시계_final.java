//class Solution {
//    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
//        // countAlarms(h, m, s) = 0시 0분 0초 "초과" ~ h:m:s "이하" 알람 횟수
//        // end - start = (start, end] 구간의 알람 횟수
//        int endCount = countAlarms(h2, m2, s2);
//        int startCount = countAlarms(h1, m1, s1);
//
//        int answer = endCount - startCount;
//
//        // 시작 시각이 겹치는 경우, 위 뺄셈에서 제외되었으므로 다시 더해줌
//        // 결과적으로 [start, end] 구간이 됨
//        if (isAlarmTime(h1, m1, s1)) {
//            answer++;
//        }
//
//        return answer;
//    }
//
//    /**
//     * 0시 0분 0초 초과 ~ h시 m분 s초 이하까지 알람이 울린 횟수
//     */
//    private int countAlarms(int h, int m, int s) {
//        // 0시 0분 0초부터 경과한 총 초
//        long t = h * 3600L + m * 60L + s;
//
//        // 0초부터 t초까지 시침-초침이 겹친 횟수
//        // 12시간(43200초)당 719회 겹침
//        long hourMeetings = (t * 719) / 43200;
//
//        // 0초부터 t초까지 분침-초침이 겹친 횟수
//        // 1시간(3600초)당 59회 겹침
//        long minuteMeetings = (t * 59) / 3600;
//
//        int total = (int)(hourMeetings + minuteMeetings);
//
//        // 12시 0분 0초(43200초)를 포함하는 경우, 시침/분침/초침이 모두 겹치므로
//        // 위 공식에서 중복 카운트된 1회를 제거
//        // (주의: 24시는 포함되지 않으므로 43200의 배수가 아니라 딱 43200 이상일 때 1번만 빼면 됨)
//        if (t >= 43200) {
//            total--;
//        }
//
//        return total;
//    }
//
//    /**
//     * 특정 시각에 알람이 울리는지 확인
//     * (초침이 시침 또는 분침과 겹치는지)
//     */
//    private boolean isAlarmTime(int h, int m, int s) {
//        // 0시 0분 0초 또는 12시 0분 0초는 무조건 겹침
//        if ((h == 0 || h == 12) && m == 0 && s == 0) {
//            return true;
//        }
//
//        // 각 바늘의 각도 계산 (degree)
//
//        // 초침: 1초에 6도 회전
//        double secondAngle = s * 6.0;
//
//        // 분침: 1분에 6도, 1초에 0.1도
//        double minuteAngle = (m * 60 + s) * 0.1;
//
//        // 시침: 1시간에 30도, 1분에 0.5도, 1초에 1/120도
//        double hourAngle = ((h % 12) * 3600 + m * 60 + s) / 120.0;
//
//        // 각도 차이 계산
//        double diffHour = Math.abs(secondAngle - hourAngle);
//        double diffMinute = Math.abs(secondAngle - minuteAngle);
//
//        // 360도 회전 고려 (예: 359도와 1도는 실제로 2도 차이)
//        diffHour = Math.min(diffHour, 360 - diffHour);
//        diffMinute = Math.min(diffMinute, 360 - diffMinute);
//
//        // 오차 범위 내에서 겹치는지 확인
//        // 부동소수점 오차를 고려하여 작은 값으로 설정
//        double epsilon = 0.01;
//
//        return diffHour < epsilon || diffMinute < epsilon;
//    }
//}
