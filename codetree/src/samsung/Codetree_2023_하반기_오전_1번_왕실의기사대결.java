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
        boolean isMoved;
        boolean isDead;
        boolean isPrevent;
        public Knight(int r, int c, int h, int w, int k, int number) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;
            this.number = number;
            isDead = false;
            isMoved = false;
            isPrevent = false;
            list = new ArrayList<>();
            MaxHP = k;
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
                    ", isMoved=" + isMoved +
                    ", isDead=" + isDead +
                    ", isPrevent=" + isPrevent +
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
            Order order = new Order(KNIGHT_THRESHOLD + curI - 1, curD);

            knightsList.get(curI-1).isPrevent = true;
            if(Logic(order)){
                updateKnightState(order);
            }
            knightsList.get(curI-1).isPrevent = false;

        }
        System.out.println(calRemainingKnight());
    }
    private static void print(){
        for (Knight knight : knightsList) {
            System.out.println(knight);
        }
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                System.out.printf("%3d ", map[i][j]);
            }
            System.out.println();
        }
        System.out.println("=====================");

    }
    private static int calRemainingKnight() {
        int result = 0;
        for (int i = 0; i < knightsList.size(); i++) {
            Knight knight = knightsList.get(i);
            if(knight.isDead)
                continue;
            result += knight.MaxHP - knight.k;
        }
//        System.out.println(knightsList);
        return result;
    }

    private static void updateKnightState(Order order) {

        for (int[] VERTEX : moves) {
            int nxtY = VERTEX[0] + dy[order.d];
            int nxtX = VERTEX[1] + dx[order.d];
            map[VERTEX[0]][VERTEX[1]] -= VERTEX[2];
            map[nxtY][nxtX] += VERTEX[2];
            Knight knight = knightsList.get(VERTEX[2] - KNIGHT_THRESHOLD);
            knight.r = nxtY;
            knight.c = nxtX;
        }



        for (int i = 0; i < knightsList.size(); i++) {
            Knight knight = knightsList.get(i);
            for (int q = 0; q < knight.list.size(); q++) {
                int[] temp = knight.list.get(q);
                temp[0] += dy[order.d];
                temp[1] += dx[order.d];
            }
            if(!knight.isMoved)
                continue;
            knight.isMoved = false;
            for (int j = 0; j < knight.list.size(); j++) {
                int[] VERTEX = knight.list.get(j);
                if(trap[VERTEX[0]][VERTEX[1]] && !knight.isPrevent){
                    knight.k--;
                    if(knight.k == 0){
                        for (int k = 0; k < knight.list.size(); k++) {
                            int [] tempVERTEX = knight.list.get(k);
                            map[tempVERTEX[0]][tempVERTEX[1]] = 0;
                        }
                        knight.isDead = true;
                        break;
                    }
                }
            }

        }
    }

    private static boolean Logic(Order order) {

        // order 예시) 100번기사 아래로 이동
        Knight knight = knightsList.get(order.i - KNIGHT_THRESHOLD);
        if(knight.isDead)
            return false;
        for (int i = 0; i < knight.list.size(); i++) {
            int[] VERTEX = knight.list.get(i);

            int nxtY = VERTEX[0] + dy[order.d];
            int nxtX = VERTEX[1] + dx[order.d];

            if(nxtY < 0 || nxtY >= L || nxtX < 0 || nxtX >= L)
                return false;

            if(map[nxtY][nxtX] == 2)
                return false;

            if(map[nxtY][nxtX] >= KNIGHT_THRESHOLD && map[nxtY][nxtX] != knight.number) {
                if (!Logic(new Order(map[nxtY][nxtX], order.d))) {
                    return false;
                }
            }
        }
        knight.isMoved = true;
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
