public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        System.out.println("=== 버그 수정 검증 테스트 ===");
        
        // 버그 케이스 1: 11:59:30 ~ 12:00:00 → 1
        int bug1 = sol.solution(11, 59, 30, 12, 0, 0);
        System.out.println("11:59:30 ~ 12:00:00: " + bug1 + " (예상: 1) " + (bug1 == 1 ? "✅" : "❌"));
        
        // 버그 케이스 2: 00:00:00 ~ 23:59:59 → 2852
        int bug2 = sol.solution(0, 0, 0, 23, 59, 59);
        System.out.println("00:00:00 ~ 23:59:59: " + bug2 + " (예상: 2852) " + (bug2 == 2852 ? "✅" : "❌"));

        // 버그 케이스 3: 11:58:59 ~ 11:59:00 -> 1
        int bug3 = sol.solution(11, 58, 59, 11, 59, 0);
        System.out.println("11:58:59 ~ 11:59:00: " + bug3 + " (예상: 1) " + (bug3 == 1 ? "✅" : "❌"));

        System.out.println("\n=== 기본 테스트 케이스 ===");
        
        // 예제 1: 0시 5분 30초 ~ 0시 7분 0초 → 2
        int result1 = sol.solution(0, 5, 30, 0, 7, 0);
        System.out.println("예제 1: " + result1 + " (예상: 2) " + (result1 == 2 ? "✅" : "❌"));
        
        // 예제 2: 12시 0분 0초 ~ 12시 0분 30초 → 1
        int result2 = sol.solution(12, 0, 0, 12, 0, 30);
        System.out.println("예제 2: " + result2 + " (예상: 1) " + (result2 == 1 ? "✅" : "❌"));
        
        // 예제 3: 0시 6분 1초 ~ 0시 6분 6초 → 0
        int result3 = sol.solution(0, 6, 1, 0, 6, 6);
        System.out.println("예제 3: " + result3 + " (예상: 0) " + (result3 == 0 ? "✅" : "❌"));
        
        // 예제 4: 11시 59분 30초 ~ 12시 0분 0초 → 1
        int result4 = sol.solution(11, 59, 30, 12, 0, 0);
        System.out.println("예제 4: " + result4 + " (예상: 1) " + (result4 == 1 ? "✅" : "❌"));
        
        System.out.println("\n모든 테스트 완료!");
    }
}

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int endCount = countAlarms(h2, m2, s2);
        int startCount = countAlarms(h1, m1, s1);
        
        int answer = endCount - startCount;
        
        if (isAlarmTime(h1, m1, s1)) {
            answer++;
        }
        
        return answer;
    }
    
    private int countAlarms(int h, int m, int s) {
        long t = h * 3600L + m * 60L + s;
        
        long hourMeetings = (t * 719) / 43200;
        long minuteMeetings = (t * 59) / 3600;
        
        int total = (int)(hourMeetings + minuteMeetings);
        
        if (t >= 43200) {
            total--;
        }
        
        return total;
    }
    
    private boolean isAlarmTime(int h, int m, int s) {
        if ((h == 0 || h == 12) && m == 0 && s == 0) {
            return true;
        }
        
        double secondAngle = s * 6.0;
        double minuteAngle = (m * 60 + s) * 0.1;
        double hourAngle = ((h % 12) * 3600 + m * 60 + s) / 120.0;
        
        double diffHour = Math.abs(secondAngle - hourAngle);
        double diffMinute = Math.abs(secondAngle - minuteAngle);
        
        diffHour = Math.min(diffHour, 360 - diffHour);
        diffMinute = Math.min(diffMinute, 360 - diffMinute);
        
        double epsilon = 0.01;
        
        return diffHour < epsilon || diffMinute < epsilon;
    }
}
