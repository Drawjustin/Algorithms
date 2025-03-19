package Baekjoon_1213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Character oddChar;
    public static void main(String[] args) throws IOException {
        String s = br.readLine();

        String result = Logic(s);

        System.out.println(result);

    }

    private static String Logic(String s) {
        int [] ary = new int[26];
        StringBuilder stb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            ary[s.charAt(i) - 'A']++;
        }

        if(!isOk(ary))
            return "I'm Sorry Hansoo";


        for (int i = 0; i < ary.length; i++) {
            for (int j = 0; j < ary[i] / 2; j++) {
                stb.append((char) (i + 'A'));
            }
        }
        String front = stb.toString();

        if(oddChar != null){
            front += oddChar;
        }
        String back = stb.reverse().toString();
        return front+back;
    }

    private static boolean isOk(int [] ary) {
        int odd = 0;
        for (int i = 0; i < ary.length; i++) {
            if(ary[i] % 2 != 0) {
                odd++;
                oddChar = (char) (i + 'A');
            }
        }
        return odd <= 1;
    }
}
