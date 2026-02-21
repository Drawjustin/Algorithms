package 레벨2.호텔대실;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class main {
    class Solution{
        public int solution(String[][] book_time){
            // 4:30
            int answer = 1;
            
            List<int []> list = new ArrayList<>();

            for (String[] strings : book_time) {
                String[] startTimeString = strings[0].split(":");
                int startTime = Integer.parseInt(startTimeString[0]) * 60 + Integer.parseInt(startTimeString[1]);
                String[] endTimeString = strings[1].split(":");
                int endTime = Integer.parseInt(endTimeString[0]) * 60 + Integer.parseInt(endTimeString[1]);
                list.add(new int []{startTime,endTime});
            }
            
            list.sort((o1, o2) -> {
                if(o1[0] == o2[0])
                    return o1[1] - o2[1]; 
                return o1[0] - o2[0];
            });
            
            // [0] 룸 번호 [1] 룸 상태, {0 - 이용가능 , 1 사용중} [2] 퇴실 시간
            List<int []> rooms = new ArrayList<>();
            rooms.add(new int []{1,0,0});


            for (int[] customer : list) {
                int availableRoomNumber = -1;
                for (int[] room : rooms) {
                    if(room[1] == 1){
                        if(room[2] <= customer[0])
                            room[1] = 0;
                    }

                    if(room[1] == 0){
                        availableRoomNumber = room[0];
                        break;
                    }
                }
                if(availableRoomNumber == -1){
                    rooms.add(new int []{rooms.size()+1,1,0});
                    availableRoomNumber = rooms.size();
                }


                int [] reservedRoom = rooms.get(availableRoomNumber-1);
                reservedRoom[1] = 1;
                reservedRoom[2] = customer[1] + 10;
            }

            return rooms.size();
        }
    }
}

/**
 * import java.util.*;
 *
 * class Solution {
 *     public int solution(String[][] book_time) {
 *         // 1. 예약 시간을 분 단위 배열로 변환 (청소 시간 10분 미리 더하기)
 *         int[][] times = new int[book_time.length][2];
 *         for (int i = 0; i < book_time.length; i++) {
 *             times[i][0] = parseTime(book_time[i][0]);
 *             times[i][1] = parseTime(book_time[i][1]) + 10; // 퇴실 + 10분
 *         }
 *
 *         // 2. 대기줄 세우기: '입실 시간' 기준으로 오름차순 정렬
 *         Arrays.sort(times, (a, b) -> {
 *             if (a[0] == b[0]) return a[1] - b[1];
 *             return a[0] - b[0];
 *         });
 *
 *         // 3. 우선순위 큐 생성: '퇴실(청소 완료) 시간'이 빠른 순으로 정렬
 *         // 여기엔 방의 '청소 완료 시간'들만 Integer로 넣을 거라 괄호가 하나야!
 *         PriorityQueue<Integer> pq = new PriorityQueue<>();
 *
 *         // 4. 손님을 순서대로 방에 배정
 *         for (int[] time : times) {
 *             // 현재 큐에 방이 있고, 가장 빨리 빈 방의 청소 완료 시간 <= 현재 손님 입실 시간이라면?
 *             if (!pq.isEmpty() && pq.peek() <= time[0]) {
 *                 pq.poll(); // 그 방을 기존 손님이 빼고(재사용)
 *             }
 *             // 현재 손님의 청소 완료 시간을 큐에 추가 (새 방을 파든, 기존 방을 이어 쓰든 무조건 추가)
 *             pq.offer(time[1]);
 *         }
 *
 *         // 5. 큐에 최종적으로 남아있는 데이터의 개수가 곧 총 필요한 방의 개수
 *         return pq.size();
 *     }
 *
 *     // "HH:MM" 문자열을 분(minute) 단위 정수로 바꾸는 헬퍼 메서드
 *     private int parseTime(String time) {
 *         String[] split = time.split(":");
 *         return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
 *     }
 * }
 */