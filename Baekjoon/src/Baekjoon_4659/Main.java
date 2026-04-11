package Baekjoon_4659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder stb = new StringBuilder();

        while (true) {
            String st = br.readLine();

            if (st.equals("end")) {
                break;
            }

            if (logic(st)) {
                stb.append("<").append(st).append(">")
                        .append(" is acceptable.\n");
            } else {
                stb.append("<").append(st).append(">")
                        .append(" is not acceptable.\n");
            }
        }

        System.out.print(stb);
    }

    static boolean logic(String s) {
        boolean hasVowel = false;
        int vowelCount = 0;
        int consonantCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean isVowel = isVowel(c);

            if (isVowel) {
                hasVowel = true;
                vowelCount++;
                consonantCount = 0;
            } else {
                consonantCount++;
                vowelCount = 0;
            }

            if (vowelCount == 3 || consonantCount == 3) {
                return false;
            }

            if (i > 0) {
                char prev = s.charAt(i - 1);
                if (prev == c && c != 'e' && c != 'o') {
                    return false;
                }
            }
        }

        return hasVowel;
    }

    static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}