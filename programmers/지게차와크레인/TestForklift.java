package 서버증설횟수;

import java.util.*;

public class TestForklift {
    static char [][] map;
    static boolean [][] isVisited;
    static int [] xAry = {1,0,-1,0};
    static int [] yAry = {0,1,0,-1};
    
    public static int solution(String[] storage, String[] requests) {
         map = new char[storage.length+2][storage[0].length()+2];
         isVisited = new boolean[storage.length+2][storage[0].length()+2];
        // 맵 초기화

        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], ' ');
        }

        for (int i = 0; i < storage.length; i++) {
            String data =  storage[i];

            for(int k = 0; k < data.length(); k++){
               map[i+1][k+1] =  data.charAt(k);
            }
        }

        // 시행 횟수 시작
        for (int i = 0; i < requests.length; i++) {
            char order = requests[i].charAt(0);
            boolean superOrder = false;
            if(requests[i].length() == 2){
                superOrder = true;
            }

            if(superOrder){
                for (int j = 0; j < map.length; j++){
                    for (int k = 0; k < map[j].length; k++){
                        if (map[j][k] == order){
                            map[j][k] = ' ';
                        }
                    }
                }
            }
            else{
                bfs(order);
            }
        }
        int result = 0;
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[i].length; j++) {
                if(map[i][j] != ' '){
                    result++;
                }
            }
        }
        return result;
    }

    private static void bfs(char order) {
        for (int i = 0; i < isVisited.length; i++) {
            Arrays.fill(isVisited[i], false);
        }

        Deque<int []> queue = new ArrayDeque<>();
        queue.add(new int []{0,0});
        isVisited[0][0] = true;

        while(!queue.isEmpty()){
            int [] current = queue.poll();

            if(isVisited[current[0]][current[1]] && !(current[0]==0 && current[1]==0) ){
                // Note: user code had: if(isVisited[...]) continue; 
                // but user set visited=true before adding.
                // so this check is mostly redundant unless added multiple times.
            }
            
            // Replicating User Logic exactly
            // The user had: if(isVisited...) continue; at the top.
            // But they also set visited when adding. 
            // So duplicates are filtered.
            
            for (int i = 0; i < 4; i++) {
                int nxtY = current[0] + yAry[i];
                int nxtX = current[1] + xAry[i];

                if(!isMoved(nxtY, nxtX)){
                    continue;
                }

                if(!(map[nxtY][nxtX] == ' ' || map[nxtY][nxtX] == order)){
                    continue;
                }
                
                // User Logic: Immediately remove
                map[nxtY][nxtX] = ' ';
                if (!isVisited[nxtY][nxtX]) {
                    isVisited[nxtY][nxtX] = true;
                    queue.add(new int []{nxtY, nxtX});
                }
            }
        }

    }

    private static boolean isMoved(int y, int x){
        return 0 <= y && y < map.length && 0 <= x && x < map[y].length;
    }
    
    public static void main(String[] args) {
        // Test Case: Recursive removal bug
        // Grid: 
        // B B B
        // B B B
        // B B B
        // (Center B is protected).
        // Request: "B"
        // Expected result: 1 (Center B remains).
        
        String[] storage = {"BBB", "BBB", "BBB"};
        String[] requests = {"B"};
        int result = solution(storage, requests);
        System.out.println("Result: " + result);
        // Correct would be 1.
        // Buggy would be 0.
        
        // Another test:
        // A A
        // A A
        // Request A. All removed. Result 0.
        String[] storage2 = {"AA", "AA"};
        String[] requests2 = {"A"};
        int result2 = solution(storage2, requests2);
        System.out.println("Result2: " + result2);
    }
}
