package 레벨2.숫자변환하기;

import java.util.ArrayDeque;
import java.util.Deque;

public class main {
    public int solution(int x, int y, int n) {
        int [] isVisited = new int[y+1];
        int answer = -1;


        Deque<int []> dq = new ArrayDeque<>();
        isVisited[x] = 0;
        dq.add(new int []{x,0});

        while (!dq.isEmpty()){
            int [] node =  dq.poll();
            if(node[0] == y) {
                answer = node[1];
                break;
            }

            int [] val1 = new int []{node[0] + n, node[1] + 1};
            int [] val2 = new int []{node[0] * 2, node[1] + 1};
            int [] val3 = new int []{node[0] * 3, node[1] + 1};
            if(val1[0] <= y && (isVisited[val1[0]] == 0 || isVisited[val1[0]] != 0 && isVisited[val1[0]] > val1[1])){
                isVisited[val1[0]] = val1[1];
                dq.add(val1);
            }if(val2[0] <= y && (isVisited[val2[0]] == 0 || isVisited[val2[0]] != 0 && isVisited[val2[0]] > val2[1])){
                isVisited[val2[0]] = val2[1];
                dq.add(val2);
            }if(val3[0] <= y && (isVisited[val3[0]] == 0 || isVisited[val3[0]] != 0 && isVisited[val3[0]] > val3[1])){
                isVisited[val3[0]] = val3[1];
                dq.add(val3);
            }

        }
        return answer;
    }
}
