package 연속된부분수열의합;

import java.util.Deque;
import java.util.LinkedList;

public class main {
    public int[] solution(int[] sequence, int k) {
        Deque<Integer> list = new LinkedList<>();
        int total = 0;
        int totalSize = 10000000;
        int [] result = new int[2];
        for (int i = 0; i < sequence.length; i++) {
            total += sequence[i];

            list.add(sequence[i]);
            while(total > k) {
                int removeValue = list.getFirst();
                total -= removeValue;
                list.removeFirst();
            }

            if(total == k && totalSize > list.size()){
                result[0] = i - list.size() +1;
                result[1] = i;
                totalSize = list.size();
            }
        }
        return result;
    }
}
