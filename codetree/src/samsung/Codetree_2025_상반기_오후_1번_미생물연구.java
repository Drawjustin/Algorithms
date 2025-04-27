package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Codetree_2025_상반기_오후_1번_미생물연구 {
    static int [][] map;
    static int N,Q;
    static int result;
    static int [] dx = {1,0,-1,0};
    static int [] dy = {0,1,0,-1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk;
    static int[] checkMap;
    static List<Group> aliveGroups = new ArrayList<>();
    static class Group{
        int Number;
        List<int []> list = new ArrayList<>();
        void addList(int [] VERTEX){
            list.add(VERTEX);
        }
        int getNumber(){
            return Number;
        }
    }
    public static void main(String[] args) throws IOException {
        init();
        for (int i = 1; i <= Q; i++) {
            MicrobialInput(i);
            cultureMediumTransfer();
            record();
            resetMap();
        }
    }

    private static void resetMap() {
        checkMap = new int[Q+1];
    }

    private static int reverseY(int y){
        return Math.abs(y-N);
    }
    private static void MicrobialInput(int number) throws IOException {
        stk = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(stk.nextToken());
        int startY = Integer.parseInt(stk.nextToken());
        int endX = Integer.parseInt(stk.nextToken());
        int endY = Integer.parseInt(stk.nextToken());

        int []start = new int[]{reverseY(endY), startX};
        int []end = new int[]{reverseY(startY),endX};


        for (int i = start[0]; i < end[0]; i++) {
            for (int j = start[1]; j < end[1]; j++) {
                map[i][j] = number;
            }
        }
        removeTwoGroup();
    }
    //사실상 1.1~ N,N까지
    private static void removeTwoGroup() {
        List<Group> list = new ArrayList<>();
        boolean[][] isVisited = new boolean[N+1][N+1];
        for (int i = 1; i <= N ; i++) {
            for (int j = 1; j <= N; j++) {
                if(!isVisited[i][j]) {
                    list.add(bfs(i,j,isVisited));
                }
            }
        }
        for (Group group : list) {
            if(checkMap[group.getNumber()] >= 2) {
                for(int [] VERTEX : group.list){
                    map[VERTEX[0]][VERTEX[1]] = 0;
                }
            }else{
                aliveGroups.add(group);
            }
        }
    }

    private static Group bfs(int i, int j, boolean[][] isVisited) {
        Group group = new Group();
        group.Number = map[i][j];
        isVisited[i][j] = true;
        Deque<int []> queue = new ArrayDeque<>();
        queue.add(new int[]{i,j});
        checkMap[group.Number]++;

        while (!queue.isEmpty()) {
            int [] curVERTEX = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nxtY = curVERTEX[0] + dy[k];
                int nxtX = curVERTEX[1] + dx[k];

                if(nxtY < 1 || nxtY > N || nxtX < 1 || nxtX > N) continue;
                if(isVisited[nxtY][nxtX]) continue;

                group.addList(new int[]{nxtY,nxtX});
                queue.add(new int[]{nxtY,nxtX});
                isVisited[nxtY][nxtX] = true;
            }
        }
        return group;
    }

    private static void cultureMediumTransfer() {
        aliveGroups.sort(new Comparator<Group>() {
            @Override
            public int compare(Group o1, Group o2) {
                return o2.list.size() - o1.list.size();
            }
        });


    }

    private static void record() {
    }

    private static void init() throws IOException {
        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        Q = Integer.parseInt(stk.nextToken());
        map = new int[N+1][N+1];
        checkMap = new int[Q+1];
    }
}
