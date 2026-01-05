package 비밀코드해독;

import 완전범죄.완전범죄DP;

public class 비밀코드해독 {
    public static void main(String[] args) {

    }

    public int solution(int n, int[][] q, int[] ans) {
        isVisited = new boolean[q.length+1];
        ary = new int[q.length+1];
        comb(1,n,q,ans);
        return secretCode;
    }

    static boolean [] isVisited;
    static int [] ary;
    static int secretCode = 0;
    private void comb(int position , int size, int [][] q, int [] ans) {
        if(position == size) {
            if(checkLogic(q,ans)){
                secretCode++;
            }
        }
        for (int i = 1; i <= size; i++) {
            if(isVisited[i]) {
                continue;
            }
            isVisited[i] = true;
            ary[position] = i;
            comb(position+1, size,q,ans);
            isVisited[i] = false;
        }
    }

    private boolean checkLogic(int[][] q,int []ans) {
        for (int i = 0; i < q.length; i++) {
            int result = 0;
            for (int j = 0; j < q[i].length; j++) {
                boolean isFined = false;
                for (int k = 1; k <= q[i].length ; k++) {
                    if(ary[k] == q[i][j]) {
                        isFined = true;
                    }
                }
                if(isFined){
                    result++;
                }
            }
            if(result != ans[i]){
                return false;
            }
        }
        return true;
    }
}
