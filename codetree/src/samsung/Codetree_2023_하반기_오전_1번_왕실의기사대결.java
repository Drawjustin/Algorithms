package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Codetree_2023_하반기_오전_1번_왕실의기사대결 {
    static int orderNumber;
    static final int KNIGHT_THRESHOLD = 100;
    static int [] dy = {-1,0,1,0};
    static int [] dx = {0,1,0,-1};
    static int [][] map;
    static boolean [][] trap;
    static int L,N,Q;
    static List<Knight> knightsList = new ArrayList<Knight>();
    static List<int []> moves;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk;
    static class Knight{
        int r;
        int c;
        int h;
        int w;
        int k;
        int number;
        int MaxHP;
        List<int []> list;
        boolean isDead;
        public Knight(int r, int c, int h, int w, int k, int number) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;
            this.number = number;
            isDead = false;
            MaxHP = k;
            list = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Knight{" +
                    "r=" + r +
                    ", c=" + c +
                    ", h=" + h +
                    ", w=" + w +
                    ", k=" + k +
                    ", number=" + number +
                    ", MaxHP=" + MaxHP +
                    ", list=" + list +
                    ", isDead=" + isDead +
                    '}';
        }
    }
    static class Order{
        int i;
        int d;
        public Order(int i, int d){
            this.i = i;
            this.d = d;
        }
    }
    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < Q; i++) {
            stk = new StringTokenizer(br.readLine());
            int curI = Integer.parseInt(stk.nextToken());
            int curD = Integer.parseInt(stk.nextToken());
            moves.clear();
            System.out.println("=======================");
            Order order = new Order(KNIGHT_THRESHOLD + curI - 1, curD);
            if(Logic(order)){
                updateKnightState(order);
            }
        }
        System.out.println(calRemainingKnight());
    }

    private static int calRemainingKnight() {
        int result = 0;
        for (int i = 0; i < knightsList.size(); i++) {
            Knight knight = knightsList.get(i);
            result += knight.MaxHP - knight.k;
        }

        System.out.println(knightsList);


        return result;
    }

    private static void updateKnightState(Order order) {

        for (int[] VERTEX : moves) {
            int nxtY = VERTEX[0] + dy[order.d];
            int nxtX = VERTEX[1] + dx[order.d];
            map[nxtY][nxtX] = VERTEX[2];
            VERTEX[0] = nxtY;
            VERTEX[1] = nxtX;
        }

        for (int i = 0; i < knightsList.size(); i++) {
            Knight knight = knightsList.get(i);

            for (int j = 0; j < knight.list.size(); j++) {
                int[] VERTEX = knight.list.get(j);
                if(trap[VERTEX[0]][VERTEX[1]]){
                    knight.k--;
                    if(knight.k == 0){
                        for (int k = 0; k < knight.list.size(); k++) {
                            int [] tempVERTEX = knight.list.get(k);
                            map[tempVERTEX[0]][tempVERTEX[1]] = 0;
                        }
                        knight.isDead = true;
                        knightsList.remove(knight);
                        break;
                    }
                }
            }

        }
    }

    private static boolean Logic(Order order) {

        System.out.printf("%d번째 명령, %d %d\n" , ++orderNumber, order.i, order.d);
        // order 예시) 100번기사 아래로 이동
        List<int []> list = new ArrayList<>();
        Knight knight = knightsList.get(order.i - KNIGHT_THRESHOLD);
        if(knight.isDead)
            return false;
        for (int i = 0; i < knight.list.size(); i++) {
            int[] VERTEX = knight.list.get(i);

            int nxtY = VERTEX[0] + dy[order.d];
            int nxtX = VERTEX[1] + dx[order.d];

            if(nxtY < 0 || nxtY >= L || nxtX < 0 || nxtX >= N)
                return false;

            if(map[nxtY][nxtX] == 2)
                return false;

            if(map[nxtY][nxtX] >= KNIGHT_THRESHOLD && map[nxtY][nxtX] != knight.number) {
                if (!Logic(new Order(map[nxtY][nxtX], order.d))) {
                    return false;
                }
            }
        }
        moves.addAll(knight.list);
        return true;
    }

    private static void init() throws IOException {
        stk = new StringTokenizer(br.readLine());
        L = Integer.parseInt(stk.nextToken());
        N = Integer.parseInt(stk.nextToken());
        Q = Integer.parseInt(stk.nextToken());
        moves = new ArrayList<>();
        map = new int[L+1][L+1];
        trap = new boolean[L+1][L+1];

        // 0 빈칸 1 함정 2 벽
        for (int i = 0; i < L; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < L; j++) {
                int x = Integer.parseInt(stk.nextToken());
                if(x != 1){
                    map[i][j] = x;
                }
                else{
                    trap[i][j] = true;
                }

            }
        }

        for (int i = 0; i < N; i++) {
            //기사는 100번부터
            stk = new StringTokenizer(br.readLine());
            int curR = Integer.parseInt(stk.nextToken())-1;
            int curC = Integer.parseInt(stk.nextToken())-1;
            int curH = Integer.parseInt(stk.nextToken());
            int curW = Integer.parseInt(stk.nextToken());
            int curK = Integer.parseInt(stk.nextToken());
            Knight curKnight = new Knight(curR, curC, curH, curW, curK, KNIGHT_THRESHOLD + i);
            knightsList.add(curKnight);
            for (int j = 0; j < curKnight.h; j++) {
                for (int k = 0; k < curKnight.w; k++) {
                    curKnight.list.add(new int [] {curKnight.r + j,curKnight.c + k, curKnight.number});
                    map[curKnight.r + j][curKnight.c + k] = KNIGHT_THRESHOLD + i;
                }
            }
        }

    }
}
