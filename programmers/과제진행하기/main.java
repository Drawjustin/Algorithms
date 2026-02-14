package 과제진행하기;

import java.util.*;

public class main {
    class Solution {
        public String[] solution(String[][] plans) {
            List<Subject> list = new ArrayList<>();

            for (int i = 0; i < plans.length; i++) {
                String name = plans[i][0];
                String[] time = plans[i][1].split(":");
                int startTime = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
                int restTime = Integer.parseInt(plans[i][2]);

                list.add(new Subject(name, startTime, restTime));
            }

            // 사용자님이 짠 내림차순 정렬 (시작 시간이 큰 게 앞으로 옴)
            Collections.sort(list);

            Stack<Subject> restSubject = new Stack<>();
            // 내림차순 리스트를 Deque에 넣고 pollLast()를 쓰면 가장 빠른 시간부터 나옵니다.
            Deque<Subject> queue = new ArrayDeque<>(list);
            List<String> result = new ArrayList<>();

            while (!queue.isEmpty()) {
                Subject cur = queue.pollLast(); // 가장 빨리 시작하는 과제 추출
                int currentTime = cur.startTime;

                if (!queue.isEmpty()) {
                    Subject next = queue.peekLast(); // 다음으로 빨리 시작할 과제

                    // 1. 다음 과제 시작 전까지 현재 과제를 다 끝낼 수 있는 경우
                    if (currentTime + cur.restTime <= next.startTime) {
                        result.add(cur.name);
                        currentTime += cur.restTime;

                        // 중단된 과제가 있다면 남은 시간 동안 처리
                        while (!restSubject.isEmpty()) {
                            Subject stop = restSubject.pop();

                            if (currentTime + stop.restTime <= next.startTime) {
                                // 중단됐던 것도 다 끝냄
                                result.add(stop.name);
                                currentTime += stop.restTime;
                            } else {
                                // 또 다 못 끝냄 (남은 시간 갱신 후 다시 스택으로)
                                stop.restTime -= (next.startTime - currentTime);
                                restSubject.push(stop);
                                break;
                            }
                        }
                    }
                    // 2. 현재 과제를 다 못 끝내고 다음 과제를 시작해야 하는 경우
                    else {
                        cur.restTime -= (next.startTime - currentTime);
                        restSubject.push(cur);
                    }
                } else {
                    // 더 이상 새로 시작할 과제가 없으면 현재 과제 바로 종료
                    result.add(cur.name);
                }
            }

            // 3. 모든 계획된 과제가 끝난 후, 스택에 남은 과제들 처리 (LIFO)
            while (!restSubject.isEmpty()) {
                result.add(restSubject.pop().name);
            }

            return result.toArray(new String[0]);
        }

        static class Subject implements Comparable<Subject> {
            String name;
            int startTime;
            int restTime;

            Subject(String name, int startTime, int restTime) {
                this.name = name;
                this.startTime = startTime;
                this.restTime = restTime;
            }

            @Override
            public int compareTo(Subject o) {
                // 사용자님 기존 코드: 내림차순
                return o.startTime - this.startTime;
            }
        }
    }
}