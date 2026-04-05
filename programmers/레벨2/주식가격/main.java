package 레벨2.주식가격;

import java.util.Comparator;
import java.util.PriorityQueue;

public class main {
    class Solution {
        public int[] solution(int[] prices) {
            int[] answer = new int [prices.length];

            PriorityQueue<int[]> sortedPrices =
                    new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
            for (int i = 0; i < prices.length; i++) {
                int curPrice = prices[i];

                while (!sortedPrices.isEmpty() && sortedPrices.peek()[0] > curPrice){
                    int [] price = sortedPrices.poll();
                    answer[price[1]] =  i - price[1];
                }
                sortedPrices.add(new int []{curPrice, i});
            }

            for(int[] i: sortedPrices){
                answer[i[1]] = prices.length - i[1] - 1;
            }

            return answer;
        }
    }
}
