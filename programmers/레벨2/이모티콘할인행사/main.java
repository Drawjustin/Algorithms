package 레벨2.이모티콘할인행사;

import java.util.*;

public class main {
    class Solution {
        // 1. 문제의 정답(최대 가입자 수, 최대 판매액)을 저장할 전역 변수
        int maxPlusUsers = 0;
        int maxSales = 0;

        // 2. 적용 가능한 4가지 할인율
        int[] discountRates = {10, 20, 30, 40};

        public int[] solution(int[][] users, int[] emoticons) {
            // 각 이모티콘에 적용될 할인율을 담을 배열 (크기는 이모티콘 개수와 동일)
            int[] discounts = new int[emoticons.length];

            // 3. DFS 탐색 시작 (첫 번째 이모티콘인 index 0부터 시작)
            dfs(0, discounts, users, emoticons);

            return new int[]{maxPlusUsers, maxSales};
        }

        // [DFS 메서드] 모든 이모티콘에 10~40% 할인율을 부여하는 모든 경우의 수를 만듭니다.
        private void dfs(int depth, int[] discounts, int[][] users, int[] emoticons) {
            // 기저 조건: 모든 이모티콘의 할인율을 다 정했을 때 (depth가 이모티콘 개수와 같아짐)
            if (depth == emoticons.length) {
                // 완성된 할인율 조합으로 결과를 계산
                calculateResult(discounts, users, emoticons);
                return;
            }

            // 현재 이모티콘(depth)에 10%, 20%, 30%, 40% 할인을 각각 적용해 봅니다.
            for (int i = 0; i < 4; i++) {
                discounts[depth] = discountRates[i]; // 할인율 적용
                dfs(depth + 1, discounts, users, emoticons); // 다음 이모티콘의 할인율을 정하러 재귀 호출
            }
        }

        // [계산 메서드] 완성된 할인율 조합(discounts)을 바탕으로 가입자 수와 판매액을 구합니다.
        private void calculateResult(int[] discounts, int[][] users, int[] emoticons) {
            int plusUsers = 0;
            int sales = 0;

            // 각 유저별로 구매 비용을 계산합니다.
            for (int[] user : users) {
                int userMinDiscount = user[0]; // 유저가 원하는 최소 할인율
                int userBudget = user[1];      // 유저의 예산 (플러스 가입 마지노선)
                int currentSpend = 0;          // 이 유저의 현재 장바구니 결제액

                for (int i = 0; i < emoticons.length; i++) {
                    // 유저가 원하는 할인율 이상일 때만 장바구니에 담습니다.
                    if (discounts[i] >= userMinDiscount) {
                        // 올바른 할인 가격 계산: 원래 가격 * (100 - 할인율) / 100
                        currentSpend += emoticons[i] * (100 - discounts[i]) / 100;
                    }
                }

                // 장바구니 결제액이 예산을 초과하거나 같으면 -> 이모티콘 플러스 가입!
                if (currentSpend >= userBudget) {
                    plusUsers++;
                } else {
                    // 예산을 넘지 않으면 -> 일반 결제 (판매액에 누적)
                    sales += currentSpend;
                }
            }

            // 문제의 우선순위 조건에 따라 최댓값을 갱신합니다.
            // 1순위: 이모티콘 플러스 가입자 수가 더 많아졌을 때
            if (plusUsers > maxPlusUsers) {
                maxPlusUsers = plusUsers;
                maxSales = sales;
            }
            // 2순위: 가입자 수는 같은데, 판매액이 더 커졌을 때
            else if (plusUsers == maxPlusUsers) {
                maxSales = Math.max(maxSales, sales);
            }
        }
    }

    //    class Solution{
//        public int[] solution(int[][] users, int[] emoticons) {
//            int[] answer = new int [2];
//
//            int plusUser = 0;
//            int emoticonSum = 0;
//
//
//            List<Integer> emoticonList = new ArrayList<>();
//            List<Integer> sales = new ArrayList<>();
//            sales.add(40);
//            sales.add(30);
//            sales.add(20);
//            sales.add(10);
//            for (int emoticon : emoticons) {
//                emoticonList.add(emoticon);
//            }
//
//            emoticonList.sort((o1, o2) -> o2 - o1);
//
//            outer:
//            while(true) {
//                Map<Integer, int []> map = new HashMap<>();
//                for (int i = 0; i < users.length; i++) {
//                    map.put(i,new int []{0,0});
//                }
//
//                // int [0] = 이모티콘 플러스 가입여부
//                // int [1] = 현재까지 이모티콘 산 금액
//                for (int i = 0; i < emoticonList.size(); i++) {
//                    for (int j = 0; j < sales.size(); j++) {
//                        int curPlusUser = 0;
//                        int curEmoticonSum = 0;
//
//                        int curEmoticonPercent = sales.get(j);
//                        for (int k = 0; k < users.length; k++) {
//                            if(users[k][0] <= curEmoticonPercent){
//                                int[] curUser = map.get(k);
//                                if(curUser[0] == 1)
//                                    continue;
//                                if(curUser[0] == 0){
//                                    int curCost = emoticonList.get(i) / curEmoticonPercent;
//                                    curUser[1] += curCost;
//                                    curEmoticonSum += curCost;
//                                }
//                                if(curUser[1] >= users[k][1]){
//                                    curUser[0] = 1;
//                                    curEmoticonSum -= curUser[1];
//                                    curPlusUser++;
//                                }
//                            }
//                        }
//
//                        if(plusUser <= curPlusUser){
//                            plusUser = curPlusUser;
//                            if(emoticonSum < curEmoticonSum){
//                                emoticonSum = curEmoticonSum;
//                            }else{
//                                break outer;
//                            }
//                        }
//
//                    }
//
//                }
//            }
//
//            answer[0] = plusUser;
//            answer[1] = emoticonSum;
//            return answer;
//        }
//    }
}
