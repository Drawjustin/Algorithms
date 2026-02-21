package 레벨2.서버증설횟수;

import java.util.PriorityQueue;

public class main {
    class Solution{
        public int solution(int [] players, int m, int k){
            int answer = 0;
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for (int i = 0; i < players.length; i++) {
                int needServer = players[i] / m;
                while(!pq.isEmpty() && pq.peek() <= i){
                    pq.poll();
                }
                if(pq.size() < needServer){
                    int addServer = needServer - pq.size();
                    for (int j = 0; j < addServer; j++) {
                        answer ++;
                        pq.add(i + k);
                    }
                }
            }

            return answer;
        }
    }
}
