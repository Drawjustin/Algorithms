package Baekjoon_13144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainV1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());
        String [] temp = br.readLine().split(" ");
        for (String s : temp) {
            list.add(Integer.parseInt(s));
        }

        Set<Integer> set = new HashSet<>();
        int result = N;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N - i; j++) {
                set.clear();
                boolean flag = false;
                for (int k = j; k <= j+i; k++) {
                    if(set.contains(list.get(k))){
                        flag = true;
                        break;
                    }
                    set.add(list.get(k));
                }
                if(!flag)
                    result++;
            }
        }
        System.out.println(result);
        
        
    }
}
