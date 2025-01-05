package Baekjoon_1943;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
public class Main {
    static class Coin{
        int price, amount;
        public Coin(int price, int amount) {
            this.price = price;
            this.amount = amount;
        }
    }
    static Coin[] coins;
    static boolean[][] DP;
    static final int MAX_PRICE = 100000;
    static int N;

    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        //각 테스트 케이스 진행
        for (int tc = 0; tc < 3; tc++) {
            N = Integer.parseInt(br.readLine());
            coins = new Coin[N];
            int sum = 0;
            //동전 정보 저장 및 동전 가치의 합 구하기
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int price = Integer.parseInt(st.nextToken());
                int amount = Integer.parseInt(st.nextToken());
                coins[i] = new Coin(price, amount);
                sum += price * amount;
            }
            //동전 가치의 합이 홀수일 때 : 동일한 가치 나누기 X
            if (sum % 2 == 1)
                bw.write("0");
            else {		//동전 가치의 합이 짝수일 때
                //목표 동전 가치를 위해 나누기 2 진행
                sum /= 2;
                DP = new boolean[N+1][sum+1];
                //동전을 사용하지 않았을 때
                DP[0][0] = true;
                //점화식 진행
                for(int i=1;i<=N;i++){
                    Coin cur = coins[i-1];
                    for(int j=0;j<=sum;j++){
                        //i-1개로 해당 동전의 가치를 만들 수 있을 때
                        if(DP[i-1][j]){
                            //동전 0 ~ k개 사용해보기
                            for(int k=0;k<=cur.amount;k++){
                                int tempAmount = j + cur.price * k;
                                if(tempAmount <= sum){
                                    DP[i][tempAmount] = true;
                                }
                            }
                        }
                    }
                }
                //만약 DP[N][동전 가치의 합 / 2]을 만들 수 있을 때
                if (DP[N][sum])
                    bw.write("1");
                else		//만들 수 없을 때
                    bw.write("0");
            }
            bw.newLine();
        }
        bw.flush();	//결과 출력
        bw.close();
        br.close();
    }
}