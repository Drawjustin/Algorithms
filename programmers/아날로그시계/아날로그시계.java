class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        // 종료 시각까지의 알람 횟수 - 시작 시각까지의 알람 횟수
        int endCount = countAlarms(h2, m2, s2);
        int startCount = countAlarms(h1, m1, s1);
        
        // 시작 시각에 정확히 겹치는 경우 추가
        if (isAlarmTime(h1, m1, s1)) {
            return endCount - startCount + 1;
        }
        
        return endCount - startCount;
    }
    
    /**
     * 0시 0분 0초부터 주어진 시각까지 알람이 울린 횟수 계산
     * 핵심: 초침이 시침/분침을 몇 번 추월했는가?
     */
    private int countAlarms(int h, int m, int s) {
        // 12시간 기준으로 변환
        h = h % 12;
        
        // 총 초로 변환
        int totalSeconds = h * 3600 + m * 60 + s;
        
        // 초침과 시침이 만나는 횟수 계산
        // 12시간(43200초) 동안: 초침 720바퀴, 시침 1바퀴 → 719번 만남
        // t초일 때 만난 횟수 = floor(719 * t / 43200)
        int hourMeetings = (719 * totalSeconds) / 43200;
        
        // 초침과 분침이 만나는 횟수 계산
        // 1시간(3600초) 동안: 초침 60바퀴, 분침 1바퀴 → 59번 만남
        // 12시간 동안: 59 * 12 = 708번 만남
        // t초일 때 만난 횟수 = floor(708 * t / 43200) + floor(t / 3600)
        // 더 정확하게: t초일 때 = floor(59 * t / 3600)
        int minuteMeetings = (59 * totalSeconds) / 3600;
        
        int total = hourMeetings + minuteMeetings;
        
        // 0시 0분 0초와 12시 0분 0초에는 3개 바늘이 모두 겹침
        // 이 경우 알람이 2번이 아니라 1번만 울리므로 중복 제거
        // 0시 정각 제거
        if (totalSeconds == 0) {
            total -= 1;
        }
        
        return total;
    }
    
    /**
     * 주어진 시각에 초침이 시침 또는 분침과 겹치는지 확인
     */
    private boolean isAlarmTime(int h, int m, int s) {
        // 0시 0분 0초 또는 12시 0분 0초
        if ((h == 0 || h == 12) && m == 0 && s == 0) {
            return true;
        }
        
        // 각 바늘의 각도 계산 (초 단위로 계산)
        // 초침: 초당 6도
        double secondAngle = s * 6.0;
        
        // 분침: 초당 0.1도 (분당 6도)
        double minuteAngle = (m * 60 + s) * 0.1;
        
        // 시침: 초당 1/120도 (시간당 30도)
        double hourAngle = ((h % 12) * 3600 + m * 60 + s) / 120.0;
        
        // 각도 정규화 (0-360)
        secondAngle = secondAngle % 360;
        minuteAngle = minuteAngle % 360;
        hourAngle = hourAngle % 360;
        
        // 오차 범위 내에서 겹치는지 확인
        double epsilon = 0.01;
        
        boolean overlapHour = Math.abs(secondAngle - hourAngle) < epsilon 
                           || Math.abs(secondAngle - hourAngle - 360) < epsilon
                           || Math.abs(secondAngle - hourAngle + 360) < epsilon;
        
        boolean overlapMinute = Math.abs(secondAngle - minuteAngle) < epsilon
                             || Math.abs(secondAngle - minuteAngle - 360) < epsilon
                             || Math.abs(secondAngle - minuteAngle + 360) < epsilon;
        
        return overlapHour || overlapMinute;
    }
}
