package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Codetree_2023_하반기_오전_1번_왕실의기사대결V2 {

    static int L,N,Q;
    static boolean [][] trapMap;
    static int [][] map;
    static int [] dy = {-1,0,1,0};
    static int [] dx = {0,1,0,-1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk;
    static List<Knight> knights = new ArrayList<>();
    static Set<Integer> movedKnights = new HashSet<>();
//    static List<Integer> movedKnights = new ArrayList<>();
    static class Knight{
        int r;
        int c;
        int h;
        int w;
        int k;
        int totalDamage;

        public Knight(int r, int c, int h, int w, int k) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;
            this.totalDamage = 0;
        }

        @Override
        public String toString() {
            return "Knight{" +
                    "r=" + r +
                    ", c=" + c +
                    ", h=" + h +
                    ", w=" + w +
                    ", k=" + k +
                    ", totalDamage=" + totalDamage +
                    '}';
        }
    }
    private static void printMap(){
        for (int i = 1; i <= L; i++) {
            for (int j = 1; j <= L; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("====================");
    }
    private static boolean attack(int knightNumber, int direction) {
        Knight knight = knights.get(knightNumber);

        if(knight.totalDamage >= knight.k)
            return false;

        for (int i = 0; i < knight.h; i++) {
            for (int j = 0; j < knight.w; j++) {
                int nxtR = knight.r + i + dy[direction];
                int nxtC = knight.c + j + dx[direction];

                if(nxtR < 1 || nxtR > L || nxtC < 1 || nxtC > L)
                    return false;

                if(map[nxtR][nxtC] == -1)
                    return false;

                if(map[nxtR][nxtC] != knightNumber && map[nxtR][nxtC] != 0){
                    if(!attack(map[nxtR][nxtC], direction))
                        return false;
                }
            }
        }

        movedKnights.add(knightNumber);
        return true;
    }


    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < Q; i++) {
            stk = new StringTokenizer(br.readLine());
            int knightNumber = Integer.parseInt(stk.nextToken());
            int direction = Integer.parseInt(stk.nextToken());
            Logic(knightNumber,direction);
        }
        System.out.println(saveKnight());
    }

    private static int saveKnight() {
        int result = 0;
        for (Knight knight : knights) {
            if(knight.totalDamage >= knight.k)
                continue;
            result += knight.totalDamage;
        }
        return result;
    }


    private static void Logic(int knightNumber, int direction) {
        if(attack(knightNumber,direction))
            updateKnightState(knightNumber,direction);
        movedKnights.clear();
    }
    private static void updateKnightState(int knightNumber,int direction) {
        for (Integer movedKnight : movedKnights) {
            //지도에서 기사 이동시키기
            moveKnight(movedKnight,direction);
        }

        for (Integer movedKnight : movedKnights) {
            //처음 공격 한 기사는 무적
            if(movedKnight == knightNumber)
                continue;
            //데미지 입히기
            damageKnight(movedKnight);
        }


    }

    private static void damageKnight(Integer movedKnight) {
        Knight knight = knights.get(movedKnight);
        int curDamage = 0;
        for (int i = 0; i < knight.h; i++) {
            for (int j = 0; j < knight.w; j++) {
                if(trapMap[knight.r + i][knight.c + j])
                    curDamage++;
            }
        }
        knight.totalDamage += curDamage;

        if(knight.totalDamage >= knight.k){
            for (int i = 0; i < knight.h; i++) {
                for (int j = 0; j < knight.w; j++) {
                    map[knight.r + i][knight.c + j] = 0;
                }
            }
        }

    }

    private static void moveKnight(Integer movedKnight, int direction) {
        Knight knight = knights.get(movedKnight);
        for (int i = 0; i < knight.h; i++) {
            for (int j = 0; j < knight.w; j++) {
                map[knight.r + i][knight.c + j] -= movedKnight;
                map[knight.r + i + dy[direction]][knight.c + j + dx[direction]] += movedKnight;
            }
        }
        knight.r += dy[direction];
        knight.c += dx[direction];
    }

    private static void init() throws IOException {
        stk = new StringTokenizer(br.readLine());
        L = Integer.parseInt(stk.nextToken());
        N = Integer.parseInt(stk.nextToken());
        Q = Integer.parseInt(stk.nextToken());


        //0 빈칸 1 함정 2 벽 -> -1 벽 0 빈칸 , 함정 맵 별도
        map = new int[L+1][L+1];
        trapMap = new boolean[L+1][L+1];
        for (int i = 1; i <= L; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 1; j <= L; j++) {
                int value = Integer.parseInt(stk.nextToken());
                if(value == 2){
                    map[i][j] = -1;
                }else if(value == 1){
                    trapMap[i][j] = true;
                }
            }
        }

        // 더미 나이트
        knights.add(new Knight(-1, -1, -1, -1, -1));

        for (int i = 1; i <= N; i++) {
            stk = new StringTokenizer(br.readLine());
            int curR = Integer.parseInt(stk.nextToken());
            int curC = Integer.parseInt(stk.nextToken());
            int curH = Integer.parseInt(stk.nextToken());
            int curW = Integer.parseInt(stk.nextToken());
            int curK = Integer.parseInt(stk.nextToken());
            Knight curKnight = new Knight(curR, curC, curH, curW, curK);
            knights.add(curKnight);

            for (int j = 0; j < curH; j++) {
                for (int k = 0; k < curW; k++) {
                    map[curR + j][curC + k] = i;
                }
            }

        }


    }
}
