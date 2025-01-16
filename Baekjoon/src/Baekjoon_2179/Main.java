package Baekjoon_2179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int result = -1;
    static String S = "";
    static String T = "";
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static Map<String,Integer> map = new HashMap<>();
    static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        int time = Integer.parseInt(br.readLine());

        for (int i = 0; i < time; i++) {
            String temp = br.readLine();
            list.add(temp);
        }


        for(int i=0; i<list.size(); i++){
            for (int j = i+1; j <list.size() ; j++) {
                Equals(list.get(i),list.get(j));
            }
        }

        System.out.println(S);
        System.out.println(T);

    }

    private static void Equals(String s1, String s2){
        int tempResult = 0;

        int length = Math.min(s1.length(),s2.length());
        for(int i =0; i<length; i++){
            if(s1.charAt(i) == s2.charAt(i)){
                tempResult++;
            }else{
                break;
            }
        }
        if(tempResult > result){
            S = s1;
            T = s2;
            result = tempResult;
        }


    }
}
