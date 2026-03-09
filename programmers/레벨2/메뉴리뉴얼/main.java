package 레벨2.메뉴리뉴얼;

import java.util.*;

public class main {
//    class Solution {
//        // 코스 길이별로 현재까지의 최대 등장 횟수를 저장 (예: 2글자 코스 중 최대 5번 등장했다면 maxCounts[2] = 5)
//        int[] maxCounts = new int[11];
//        Map<String, Integer> map = new HashMap<>();
//
//        public String[] solution(String[] orders, int[] course) {
//            for (String order : orders) {
//                char[] chars = order.toCharArray();
//                Arrays.sort(chars); // ABC, CBA 방지
//
//                // 각 order에 대해 가능한 모든 조합을 재귀로 생성
//                for (int len : course) {
//                    if (order.length() >= len) {
//                        combine(chars, 0, new StringBuilder(), len);
//                    }
//                }
//            }
//
//            List<String> answerList = new ArrayList<>();
//            for (String key : map.keySet()) {
//                int len = key.length();
//                int count = map.get(key);
//                // 2번 이상 주문되었고, 해당 길이에서 가장 많이 주문된 메뉴인 경우만
//                if (count >= 2 && count == maxCounts[len]) {
//                    answerList.add(key);
//                }
//            }
//
//            Collections.sort(answerList);
//            return answerList.toArray(new String[0]);
//        }
//
//        // 백트래킹을 이용한 효율적인 조합 생성
//        private void combine(char[] chars, int start, StringBuilder sb, int len) {
//            if (sb.length() == len) {
//                String res = sb.toString();
//                int count = map.getOrDefault(res, 0) + 1;
//                map.put(res, count);
//                // 조합을 만들 때마다 실시간으로 해당 길이의 최댓값 갱신
//                maxCounts[len] = Math.max(maxCounts[len], count);
//                return;
//            }
//
//            for (int i = start; i < chars.length; i++) {
//                sb.append(chars[i]);
//                combine(chars, i + 1, sb, len);
//                sb.deleteCharAt(sb.length() - 1); // 백트래킹 (가지치기)
//            }
//        }
//    }

    class Solution {
        public String[] solution(String[] orders, int[] course) {
            Map<String, Integer> map = new HashMap<>();

            for (String order : orders) {
                // 1. 문자열 정렬 (중요!)
                char[] chars = order.toCharArray();
                Arrays.sort(chars);
                String sortedOrder = new String(chars);

                // 2. 필요한 코스 길이(j)만 순회
                for (int j : course) {
                    if (sortedOrder.length() < j) continue;

                    int[] idx = new int[sortedOrder.length()];
                    for (int k = 0; k < j; k++) idx[idx.length - 1 - k] = 1;

                    do {
                        StringBuilder stb = new StringBuilder();
                        for (int k = 0; k < idx.length; k++) {
                            if (idx[k] == 1) stb.append(sortedOrder.charAt(k));
                        }
                        String key = stb.toString();
                        map.put(key, map.getOrDefault(key, 0) + 1);
                    } while (np(idx));
                }
            }

            // ---------------------------------------------------------
            // 3. Stream을 제거하고 개선한 빈도수 찾기 로직 (O(N))
            // ---------------------------------------------------------
            int[] maxCounts = new int[11]; // 메뉴의 최대 길이는 10 이하

            // 첫 번째 Map 순회: 각 코스 길이별로 가장 많이 주문된 횟수 파악
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                int len = entry.getKey().length();
                int count = entry.getValue();
                maxCounts[len] = Math.max(maxCounts[len], count);
            }

            List<String> answerList = new ArrayList<>();
            // 두 번째 Map 순회: 최소 2명 이상 & 최댓값과 일치하는 메뉴 찾기
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                int len = entry.getKey().length();
                int count = entry.getValue();

                if (count >= 2 && count == maxCounts[len]) {
                    answerList.add(entry.getKey());
                }
            }

            Collections.sort(answerList); // 마지막에 사전순 정렬
            return answerList.toArray(new String[0]);
        }

        public boolean np (int [] ary){
            int i = ary.length -1;
            while(i > 0 && ary[i-1] >= ary[i]) i--;

            if(i == 0) return false;

            int j = ary.length -1;
            while(ary[i-1] >= ary[j]) j--;
            swap(ary, i-1, j);

            int k = ary.length -1;
            while(i < k) swap(ary, i++, k--);
            return true;
        }
        public void swap(int [] ary,int  i,int  j){
            int temp = ary[i];
            ary[i] = ary[j];
            ary[j] = temp;
        }
    }
}
