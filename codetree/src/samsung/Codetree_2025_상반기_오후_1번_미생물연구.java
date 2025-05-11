package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Codetree_2025_상반기_오후_1번_미생물연구 {
    static int[][] map;
    static int N, Q;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk;
    static List<Group> aliveGroups = new ArrayList<>();

    static class Group {
        int Number;
        List<int[]> list = new ArrayList<>();
        void addList(int[] v) { list.add(v); }
        int size() { return list.size(); }
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

    private static void init() throws IOException {
        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        Q = Integer.parseInt(stk.nextToken());
        map = new int[N][N];
    }

    private static void resetMap() {
        aliveGroups.clear();
    }

    private static void MicrobialInput(int number) throws IOException {
        stk = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(stk.nextToken());
        int startY = Integer.parseInt(stk.nextToken());
        int endX = Integer.parseInt(stk.nextToken());
        int endY = Integer.parseInt(stk.nextToken());
        for (int i = startY; i < endY; i++) {
            for (int j = startX; j < endX; j++) {
                map[i][j] = number;
            }
        }
        removeTwoGroup();
    }

    private static void removeTwoGroup() {
        List<Group> list = new ArrayList<>();
        boolean[][] isVisited = new boolean[N][N];
        boolean[] seen = new boolean[Q+1];
        boolean[] dead = new boolean[Q+1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!isVisited[i][j] && map[i][j] != 0) {
                    list.add(bfs(i, j, isVisited));
                }
            }
        }
        for (Group g : list) {
            int id = g.Number;
            if (seen[id]) dead[id] = true;
            seen[id] = true;
        }
        for (Group g : list) {
            int id = g.Number;
            if (dead[id]) {
                for (int[] v : g.list) map[v[0]][v[1]] = 0;
            } else {
                aliveGroups.add(g);
            }
        }
    }

    private static Group bfs(int i, int j, boolean[][] isVisited) {
        int id = map[i][j];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        isVisited[i][j] = true;
        Group g = new Group();
        g.Number = id;
        g.addList(new int[]{i,j});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nxtY = cur[0] + dy[k];
                int nxtX = cur[1] + dx[k];
                if (nxtY < 0 || nxtY >= N || nxtX < 0 || nxtX >= N) continue;
                if (isVisited[nxtY][nxtX] || map[nxtY][nxtX] != id) continue;
                isVisited[nxtY][nxtX] = true;
                queue.add(new int[]{nxtY, nxtX});
                g.addList(new int[]{nxtY, nxtX});
            }
        }
        return g;
    }

    private static void cultureMediumTransfer() {
        aliveGroups.sort((a, b) -> {
            if (b.size() != a.size()) return b.size() - a.size();
            return a.Number - b.Number;
        });

        int[][] newMap = new int[N][N];
        for (Group g : aliveGroups) {
            boolean moved = false;
            int[] base = g.list.get(0);
            for (int x = 0; x < N && !moved; x++) {
                for (int y = 0; y < N && !moved; y++) {
                    if (canMove(newMap, g, y, x, base)) {
                        moveGroup(newMap, g, y, x, base);
                        moved = true;
                    }
                }
            }
        }
        map = newMap;
    }


    private static boolean canMove(int[][] nm, Group g, int startY, int startX, int[] base) {
        for (int[] v : g.list) {
            int nxtY = startY + (v[0] - base[0]);
            int nxtX = startX + (v[1] - base[1]);
            if (nxtY < 0 || nxtY >= N || nxtX < 0 || nxtX >= N) return false;
            if (nm[nxtY][nxtX] != 0) return false;
        }
        return true;
    }

    private static void moveGroup(int[][] newMap, Group g, int startY, int startX, int[] base) {
        for (int[] v : g.list) {
            newMap[startY + (v[0] - base[0])][startX + (v[1] - base[1])] = g.Number;
        }
    }
    private static void record() {

        boolean[][] isVisited = new boolean[Q+1][Q+1];
        int tmp = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int id1 = map[i][j];
                if (id1 == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int nxtY = i + dy[k];
                    int nxtX = j + dx[k];
                    if (nxtY < 0 || nxtY >= N || nxtX < 0 || nxtX >= N) continue;

                    int id2 = map[nxtY][nxtX];
                    if (id2 == 0 || id1 == id2 || isVisited[id1][id2]) continue;
                    tmp += getGroupSize(id1) * getGroupSize(id2);
                    isVisited[id1][id2] = isVisited[id2][id1] = true;
                }
            }
        }
        System.out.println(tmp);
    }

    private static int getGroupSize(int id) {
        for (Group g : aliveGroups)
            if (g.Number == id)
                return g.size();
        return 0;
    }
}
