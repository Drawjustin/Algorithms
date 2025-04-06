package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Collections.rotate;

public class Codeetree_2023_상반기_오후_1번_메이즈러너 {
    static int[][] map ;
    static int[][] tempMap;

    static int [] dx = {1,0,-1,0};
    static int [] dy = {0,1,0,-1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M,K;
    static int [] door = new int [2];
    static int result;
    static List<int []> people = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < K; i++) {
            loop();
            if(gameEnd())
                break;
        }
        System.out.println(result);
        System.out.println(door[0]+" "+door[1]);
    }

    private static boolean gameEnd() {
        boolean result = true;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(map[i][j] >= 100)
                    return false;
            }
        }
        return result;
    }

    private static void loop() {
        for (int i = 0; i < people.size(); i++) {
            int [] curPerson = people.get(i);

            if(curPerson[4] == 1)
                continue;

            int curDistance = calDistance(people.get(i),door);
            PriorityQueue<int []> pq = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[2] == o2[2]){
                        return o2[3] - o1[3];
                    }
                    return o1[2] - o2[2];
                }
            });
            for (int j = 0; j < 4; j++) {
                int [] nxtVertex = canGo(curPerson,door,j, curDistance);
                if(nxtVertex != null)
                    pq.add(nxtVertex);

                if(!pq.isEmpty()){
                    int[] nxtPerson = pq.poll();

                    //목적지에 도달했는지
                    if(nxtPerson[0] == door[0] && nxtPerson[1] == door[1]){
                        result++;
                        map[curPerson[0]][curPerson[1]] -= 100;
                        nxtPerson[4] = 1;
                        break;
                    }
                    //목적지에 도달하지는 못햇는데 이동은했을때
                    map[nxtPerson[0]][nxtPerson[1]] += 100;
                    map[curPerson[0]][curPerson[1]] -= 100;
                    result++;
                    curPerson[0] = nxtPerson[0];
                    curPerson[1] = nxtPerson[1];
                    curPerson[2] = nxtPerson[2];
                }

            }
        }


        // 크기 2부터 N까지의 정사각형 검사
        for (int size = 2; size <= N; size++) {
            // 왼쪽 상단 모서리 후보 찾기
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int startRow = door[0] - size + 1 + i;
                    int startCol = door[1] - size + 1 + j;


                    boolean isPerson = false;
                    c :
                    for (int k = startRow; k < startRow + size; k++) {
                        for (int l = startCol; l < startCol + size; l++) {
                            if(k < 1 || k > N || l < 1 || l > N) {
                                isPerson = false;
                               break c;
                            }
                            if(map[k][l] >= 100)
                                isPerson = true;
                        }
                    }
                    if(isPerson){
                        rotate(startRow,startCol,size);
                    }
                }
            }
        }
    }

    private static void rotate(int startRow, int startCol, int size) {
        for (int i = startRow; i < startRow + size; i++) {
            for (int j = startCol; j < startCol + size; j++) {
                if(map[i][j] >= 100){
                    for (int[] person : people) {
                        if(person[0] == i && person[1] == j) {
                            person[0] = j;
                            person[1] = size+1-i;
                        }
                    }
                }
                if(map[i][j] == -1){
                    door[0] = j;
                    door[1] = size+1-i;
                }
                tempMap[j][size+1-i] = map[i][j];
            }
        }

        for (int i = startRow; i < startRow + size; i++) {
            for (int j = startCol; j < startCol + size; j++) {
                map[i][j] = tempMap[i][j];
                if(map[i][j] >= 1 && map[i][j] <= 9) {
                    map[i][j]--;
                }
            }
        }

        for (int[] person : people) {
            person[2] = calDistance(person,door);
        }
        System.out.println(door[0] + " " + door[1]);
    }

    private static int calDistance(int[] person, int[] door) {
        return Math.abs(person[0] - door[0]) + Math.abs(person[1] - door[1]);
    }

    private static int[] canGo(int [] person, int[] door, int dir, int curDistance) {
        int [] nxtPerson = new int[5];
        int nxtY = person[0] + dy[dir];
        int nxtX = person[1] + dx[dir];

        if(nxtY < 1 || nxtY > N || nxtX < 1 || nxtX > N)
            return null;

        if(map[nxtY][nxtX] >= 1 && map[nxtY][nxtX] <= 9)
            return null;

        nxtPerson[0] = nxtY;
        nxtPerson[1] = nxtX;
        nxtPerson[2] = calDistance(new int[]{nxtY,nxtX},door);
        nxtPerson[3] = (dir == 1 || dir == 3) ? 1 : 0;
        return nxtPerson;
    }


    private static void init() throws IOException{
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());

        map = new int[N+1][N+1];
        tempMap = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(stk.nextToken());
            int x = Integer.parseInt(stk.nextToken());
            map[y][x] += 100;
            int curDistance = calDistance(new int[]{y,x},door);
            people.add(new int []{y,x,curDistance,0,0});
        }
        stk = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(stk.nextToken());
        int x = Integer.parseInt(stk.nextToken());
        map[y][x] = -1;
        door[0] = y;
        door[1] = x;

    }
}
