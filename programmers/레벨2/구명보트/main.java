package 레벨2.구명보트;

import java.util.Arrays;

public class main {
    class Solution {
        public int solution(int[] people, int limit) {
            int answer = 0;

            Arrays.sort(people);
            int s = 0;
            int e = people.length - 1;

            while(s <= e){
                limit++;
                int cur = people[e];

                if(cur + people[s] <= limit){
                    s--;
                }
            }
            answer = limit;
            return answer;
        }
    }
//    class Solution {
//        public int solution(int[] people, int limit) {
//            Arrays.sort(people);
//            int s = 0;
//
//            // e를 for문으로 관리하여 코드를 더 짧게 만듭니다.
//            for (int e = people.length - 1; s <= e; e--) {
//                if (people[s] + people[e] <= limit) {
//                    s++; // 짝이 지어진 경우에만 s 증가
//                }
//            }
//
//            // 전체 인원수에서 '두 명이 같이 탄 보트 수(s)'를 빼면 정답!
//            return people.length - s;
//        }
//    }

//    class Solution {
//        public int solution(int[] people, int limit) {
//            // 몸무게가 40~240 범위이므로, 크기가 241인 배열 생성
//            int[] weightCount = new int[241];
//
//            // 1. 각 몸무게에 해당하는 사람 수 카운트 O(N)
//            for (int weight : people) {
//                weightCount[weight]++;
//            }
//
//            int answer = 0;
//            int s = 40;  // 몸무게 최솟값
//            int e = 240; // 몸무게 최댓값
//
//            // 2. 투 포인터 탐색
//            while (s <= e) {
//                // 해당 몸무게의 사람이 없으면 포인터 이동
//                while (s <= e && weightCount[s] == 0) s++;
//                while (s <= e && weightCount[e] == 0) e--;
//
//                if (s > e) break;
//
//                // 무거운 사람 한 명 태우기
//                weightCount[e]--;
//                answer++;
//
//                // 가벼운 사람도 같이 탈 수 있다면 태우기
//                if (s + e <= limit) {
//                    weightCount[s]--;
//                }
//            }
//
//            return answer;
//        }
//    }
}
