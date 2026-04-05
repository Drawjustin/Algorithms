package Baekjoon_19637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringBuilder stb = new StringBuilder();
        int N = 0;
        int M = 0;
        String input = br.readLine();
        StringTokenizer stk = new StringTokenizer(input);
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        List<Order>  orders = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            String name = stk.nextToken();
            int power =  Integer.parseInt(stk.nextToken());
            orders.add(new Order(power,name));
        }
        for (int i = 0; i < M; i++) {
            int target =  Integer.parseInt(br.readLine());
            stb.append(orders.get(lowerBound(orders, target)).name).append('\n');
        }
        System.out.println(stb);

    }
    static class Order {
        int power;
        String name;
        public Order(int power, String name) {
            this.power = power;
            this.name = name;
        }
    }

    private static int lowerBound(List<Order> orders, int target) {
        // 0 10
        int left = 0;
        int right = orders.size();

        while(left < right){
            int mid = (left + right) / 2;
            if(orders.get(mid).power >= target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
}
