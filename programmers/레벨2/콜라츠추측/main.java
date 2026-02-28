package 레벨2.콜라츠추측;

import java.util.ArrayList;
import java.util.List;

public class main {
        public double[] solution(int k, int[][] ranges) {
            // 1. 우박수열 구하기 (인덱스가 곧 x좌표이므로 y값만 저장합니다)
            List<Integer> yValues = new ArrayList<>();
            while (k > 1) {
                yValues.add(k);
                if (k % 2 == 0) {
                    k /= 2;
                } else {
                    k = k * 3 + 1;
                }
            }
            yValues.add(1); // 마지막 1 추가

            int n = yValues.size() - 1; // 최대 x 좌표 (마지막 인덱스)

            // 2. 정적분 누적 합(Prefix Sum) 미리 구해두기 (속도 최적화의 핵심!)
            // areas[i]는 x=0 부터 x=i 까지의 전체 넓이입니다.
            double[] areas = new double[n + 1];
            for (int i = 1; i <= n; i++) {
                double y1 = yValues.get(i - 1);
                double y2 = yValues.get(i);
                // 사다리꼴 넓이 = (윗변 + 아랫변) / 2.0  (밑변은 항상 1이므로 생략)
                double currentArea = (y1 + y2) / 2.0;

                // 이전까지의 넓이에 현재 칸의 넓이를 더해서 누적
                areas[i] = areas[i - 1] + currentArea;
            }

            // 3. 주어진 구간(ranges)에 대한 정답 구하기
            double[] answer = new double[ranges.length];
            for (int i = 0; i < ranges.length; i++) {
                int start = ranges[i][0];
                int end = n + ranges[i][1]; // n은 최대 x좌표, ranges[i][1]은 음수 오프셋

                // 유효하지 않은 구간인 경우
                if (start > end) {
                    answer[i] = -1.0;
                } else {
                    // 구간 [start, end]의 넓이 = (end까지의 누적넓이) - (start까지의 누적넓이)
                    // 반복문 없이 단 한 번의 뺄셈으로 넓이를 구합니다!
                    answer[i] = areas[end] - areas[start];
                }
            }

            return answer;
        }
    //    public double[] solution(int k, int[][] ranges) {
//        double[] answer = new double [ranges.length];
//
//
//
//        List<int []> list = new ArrayList<>();
//
//        int x = 0;
//        list.add(new int [] {k,x++});
//        while(k != 1){
//            if(k % 2 == 0){
//                k /= 2;
//                list.add(new int [] {k,x++});
//            }else{
//                k = k * 3 +1;
//                list.add(new int [] {k,x++});
//            }
//        }
//
//        for (int i = 0; i < ranges.length; i++) {
//            answer[i] = cal(list,ranges[i][0], list.size() + ranges[i][1] - 1);
//        }
//
//
//
//        return answer;
//    }
//    public static double cal(List<int []> list, int start, int end){
//        double sum = 0;
//
//        if(start > end)
//            return -1;
//
//        for (int i = start; i < end; i++) {
//            sum += Math.min(list.get(i)[0],list.get(i+1)[0]);
//            if(list.get(i)[0] == list.get(i+1)[0]){
//                sum += Math.abs(list.get(i)[0] - list.get(i+1)[0]);
//            }else{
//                sum += (double) Math.abs(list.get(i)[0] - list.get(i+1)[0]) / 2;
//            }
//        }
//        return sum;
//    }
}
