    package Baekjoon_16234;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.*;

    public class Main {
        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static int N,L,R;
        static int [][] map;
        static int [] dx = {1,0,-1,0};
        static int [] dy = {0,1,0,-1};
        /**
         * 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
         * 위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
         * 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
         * 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
         * 연합을 해체하고, 모든 국경선을 닫는다.
         */
        public static void main(String[] args) throws IOException {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stk.nextToken());
            L = Integer.parseInt(stk.nextToken());
            R = Integer.parseInt(stk.nextToken());
            map = new int [N][N];

            for (int i = 0; i < N; i++) {
                stk = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(stk.nextToken());
                }
            }

            boolean isMoved = false;
            int result = 0;
            do{
                isMoved = false;
                boolean [][] movedMap = new boolean[N][N];

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if(!movedMap[i][j])
                            isMoved = Moving(i,j,movedMap) || isMoved;
                    }
                }
                if(isMoved)
                    result++;

            }while(isMoved);

            System.out.println(result);

        }

        private static boolean Moving(int startY, int startX, boolean [][] movedMap) {
            boolean isMoved = false;
            int totalSize = 0;
            /**
             * [0] = y, [1] = x
             */


            List<int []> list = new ArrayList<>();
            Deque<int []> deque = new ArrayDeque<>();

            movedMap[startY][startX] = true;
            totalSize += map[startY][startX];
            list.add(new int [] {startY,startX});
            deque.add(new int [] {startY,startX});
            while(!deque.isEmpty()){
                int [] curCountry = deque.poll();

                for (int i = 0; i < 4; i++) {
                    int nxtY = curCountry[0] + dy[i];
                    int nxtX = curCountry[1] + dx[i];

                    if(nxtY < 0 || nxtY >= N || nxtX < 0 || nxtX >= N)
                        continue;
                    if(movedMap[nxtY][nxtX])
                        continue;
                    int diff = Math.abs(map[curCountry[0]][curCountry[1]] - map[nxtY][nxtX]);

                    if (diff >= L && diff <= R) {
                        movedMap[nxtY][nxtX] = true;
                        deque.add(new int[]{nxtY, nxtX});
                        totalSize += map[nxtY][nxtX];
                        list.add(new int[]{nxtY, nxtX});
                    }


                }
            }
            if(list.size() >= 2)
                isMoved = true;

            int balancedSize = totalSize / list.size();
            for (int[] country : list) {
                map[country[0]][country[1]] = balancedSize;
            }
            return isMoved;
        }
    }
