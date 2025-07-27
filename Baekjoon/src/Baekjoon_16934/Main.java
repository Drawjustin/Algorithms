package Baekjoon_16934;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<String, Integer> duplicateMap = new HashMap<>(); // 이름 중복 카운팅
        Set<String>[] prefixSet = new HashSet[21]; // prefix 저장

        for (int i = 0; i < 21; i++) {
            prefixSet[i] = new HashSet<>();
        }

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String word = br.readLine();

            // 이미 동일한 이름이 등록된 경우
            if (duplicateMap.containsKey(word)) {
                int count = duplicateMap.get(word) + 1;
                duplicateMap.put(word, count);
                bw.write(word + count + "\n");
                continue;
            }

            // 접두어 중 가장 짧고 유일한 걸 찾기
            String prefix = word; // 디폴트로 전체 단어
            for (int j = 1; j <= word.length(); j++) {
                String sub = word.substring(0, j);
                if (!prefixSet[j].contains(sub)) {
                    prefix = sub;
                    break;
                }
            }

            bw.write(prefix + "\n");

            // prefix 등록
            for (int j = 1; j <= word.length(); j++) {
                prefixSet[j].add(word.substring(0, j));
            }

            duplicateMap.put(word, 1);
        }

        bw.flush();
        bw.close();
    }
}
