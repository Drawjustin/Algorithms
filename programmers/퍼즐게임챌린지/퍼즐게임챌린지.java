package 퍼즐게임챌린지;

public class 퍼즐게임챌린지 {
    public int solution(int[] diffs, int[] times, long limit) {
        // 11:14 시작
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int diff : diffs) {
            min = Math.min(min, diff);
            max = Math.max(max, diff);
        }
        binary_search(min,max,diffs,times,limit);
        return ans;
    }
    static int ans = 0;
    public static void binary_search(int start, int end, int[] diffs, int [] times, long limit){
        if(start > end){
            return;
        }
        // 1 ~ 100 사이라면 50
        int mid = (start + end) / 2;

        if(solve(diffs,times,limit,mid)){
            ans = mid;
            binary_search(start, mid - 1, diffs, times, limit);
        }else{
            binary_search(mid + 1, end, diffs, times, limit);
        }
    }

    private static boolean solve(int[] diffs, int[] times, long limit, int mid) {
        long curLimit = 0L;
        int curResult = mid;
        for (int i = 0; i < diffs.length; i++) {
            // 바로 풀 수 있음
            if(diffs[i] <= curResult){
                curLimit += times[i];
            }else {
                // n번 틀려야함.
                int mistake = diffs[i]-curResult;
                if(i == 0){
                    curLimit += (long) times[i] *(mistake+1);
                }else{
                    curLimit += (long) times[i] *(mistake+1) + (long) times[i - 1] * mistake;
                }
            }
        }
        return limit >= curLimit;
    }
    // 11:27 해결
}
