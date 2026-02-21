package 레벨2.서버증설횟수;

public class test {
    public static void main(String[] args) {
        서버증설횟수 sol = new 서버증설횟수();
        int[] players = {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        int m = 3;
        int k = 5;
        
        // 디버깅용 출력
        int totalAdded = 0;
        int[] servers = new int[24];
        
        System.out.println("시간 | 이용자 | 필요 | 사용가능 | 증설");
        System.out.println("-----|--------|------|----------|------");
        
        for(int i = 0; i < players.length; i++){
            int availableServers = 0;
            for(int j = Math.max(0, i - k + 1); j <= i; j++){
                availableServers += servers[j];
            }
            
            int needed = (players[i] + m - 1) / m;
            int toAdd = Math.max(0, needed - availableServers);
            
            servers[i] = toAdd;
            totalAdded += toAdd;
            
            System.out.printf("%4d | %6d | %4d | %8d | %4d\n", 
                i, players[i], needed, availableServers, toAdd);
        }
        
        System.out.println("\n총 증설 횟수: " + totalAdded);
        System.out.println("기댓값: 7");
        
        int result = sol.solution(players, m, k);
        System.out.println("solution 결과: " + result);
    }
}
