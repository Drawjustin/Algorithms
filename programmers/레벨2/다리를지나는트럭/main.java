package 레벨2.다리를지나는트럭;

import java.util.ArrayDeque;
import java.util.Deque;
public class main {
    class Solution {
        public int solution(int bridge_length, int weight, int[] truck_weights) {
            int time = 0;
            int currentWeight = 0;

            Deque<Integer> bridge = new ArrayDeque<>();
            for (int i = 0; i < bridge_length; i++) {
                bridge.offer(0);
            }

            int idx = 0;

            while (idx < truck_weights.length) {
                time++;

                currentWeight -= bridge.poll();

                if (currentWeight + truck_weights[idx] <= weight) {
                    bridge.offer(truck_weights[idx]);
                    currentWeight += truck_weights[idx];
                    idx++;
                } else {
                    bridge.offer(0);
                }
            }

            return time + bridge_length;
        }
    }
}
