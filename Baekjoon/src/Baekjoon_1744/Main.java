package Baekjoon_1744;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    static List<Integer> minusList = new ArrayList<>();
    static List<Integer> plusList = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        boolean isZero = false;
        for (int i = 0; i < N; i++) {
            int curV = Integer.parseInt(br.readLine());

            if(curV < 0 ){
                minusList.add(curV);
            }
            else if(curV > 0){
                plusList.add(curV);
            }else{
                isZero = true;
            }

        }

        Collections.sort(minusList);
        plusList.sort((o1, o2) -> o2 - o1);

        int result = 0;
        for (int i = 0; i < minusList.size(); i+=2) {
            if(i + 1 == minusList.size()){
                if(!isZero)
                    result += minusList.get(i);
                break;
            }
            result += minusList.get(i) * minusList.get(i+1);
        }
        for (int i = 0; i < plusList.size(); i+=2) {
            if(i + 1 == plusList.size()){
                result += plusList.get(i);
                break;
            }
            if(plusList.get(i) == 1 || plusList.get(i+1) == 1){
                result += (plusList.get(i) + plusList.get(i+1));
                continue;
            }
            result += plusList.get(i) * plusList.get(i+1);
        }

        System.out.println(result);

    }
}
