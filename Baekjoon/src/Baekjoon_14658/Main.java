package Baekjoon_14658;

import java.io.*;
import java.util.*;

public class Main {
    private static int [] dx = {1,0,-1,0};
    private static int [] dy = {0,1,0,-1};
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {

        StringTokenizer stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int L = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());

        List<int []> list = new ArrayList<>();

        for(int i=0; i<K; i++){
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            list.add(new int[]{y,x});
        }

        Collections.sort(list,new Comparator<int []>(){
            @Override
            public int compare(int [] a1, int [] b1){
                if(a1[0] == b1[0])
                    return a1[1] - b1[1];
                return a1[0] - b1[0];
            }
        });


        int Maxstar = 0;

        for(int [] star : list){
            int y = star[0];
            int x = star[1];

            for(int i=0; i<L; i++){
                int nowCanGuardStar = 0;
                int xMaxguardSize = x+i;
                int xMinguardSize = x-(L)+i;
                int yMaxguardSize = y+(L);
                int yMinguardSize = y;


                for(int [] star2 : list){
                    if(star2[0] >= yMinguardSize && star2[0] <= yMaxguardSize && star2[1] >= xMinguardSize && star2[1] <= xMaxguardSize){
                        nowCanGuardStar++;
                    }
                }
                Maxstar = Math.max(nowCanGuardStar,Maxstar);
            }

        }

        System.out.println(K-Maxstar);

    }
}
