package 레벨2.순위검색;

import java.util.*;

public class main {
    class Solution {
        // 조건 문자열 조합을 Key로, 점수 리스트를 Value로 갖는 맵
        Map<String, List<Integer>> map = new HashMap<>();

        public int[] solution(String[] info, String[] query) {
            int[] answer = new int[query.length];

            // 1. 모든 지원자 정보로 16가지 경우의 수(조합) 만들기
            for (int i = 0; i < info.length; i++) {
                String[] p = info[i].split(" ");
                // DFS 방식의 재귀 함수로 16개 조합 생성
                makeCombination("", 0, p);
            }

            // 2. 이분 탐색을 위해 맵에 들어있는 모든 점수 리스트를 오름차순 정렬
            for (String key : map.keySet()) {
                Collections.sort(map.get(key));
            }

            // 3. 쿼리 분석 및 이분 탐색으로 정답 찾기
            for (int i = 0; i < query.length; i++) {
                // " and "를 없애고 검색하기 쉬운 배열로 만듦
                query[i] = query[i].replaceAll(" and ", "");
                String[] q = query[i].split(" ");

                // 앞의 4개 조건은 하나의 Key로 합침 (예: "javabackendjuniorpizza")
                String key = q[0] + q[1] + q[2] + q[3];
                int targetScore = Integer.parseInt(q[4]);

                // 해당 조건의 점수 리스트를 가져와서 이분 탐색 수행
                if (map.containsKey(key)) {
                    List<Integer> scoreList = map.get(key);
                    answer[i] = binarySearch(scoreList, targetScore);
                } else {
                    answer[i] = 0; // 조건에 맞는 사람이 아예 없으면 0명
                }
            }

            return answer;
        }

        // --- 조합을 만드는 재귀 함수 (DFS) ---
        private void makeCombination(String str, int depth, String[] info) {
            // 4가지 조건을 다 조합했으면 맵에 점수 넣고 종료
            if (depth == 4) {
                map.computeIfAbsent(str, k -> new ArrayList<>());
                map.get(str).add(Integer.parseInt(info[4]));
                return;
            }

            // 현재 조건을 선택하는 경우
            makeCombination(str + info[depth], depth + 1, info);
            // 현재 조건을 선택하지 않고 "-"로 처리하는 경우
            makeCombination(str + "-", depth + 1, info);
        }

        // --- 기준 점수 이상인 사람 수를 찾는 이분 탐색 (Lower Bound) ---
        private int binarySearch(List<Integer> list, int target) {
            int left = 0;
            int right = list.size() - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                // 중간 점수가 목표 점수보다 작으면, 오른쪽(더 큰 점수들) 탐색
                if (list.get(mid) < target) {
                    left = mid + 1;
                } else { // 크거나 같으면, 조건은 만족하지만 더 앞쪽(최소 기준점)을 찾기 위해 왼쪽 탐색
                    right = mid - 1;
                }
            }

            // 전체 길이에서 기준점(left) 인덱스를 빼면 타겟 점수 이상인 사람의 수가 나옴
            return list.size() - left;
        }
    }
}
