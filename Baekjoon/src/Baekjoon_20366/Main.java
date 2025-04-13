package Baekjoon_20366;

import java.util.*;
import java.io.*;

public class Main {

    static int n, res;
    static int[] diameters;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        res = Integer.MAX_VALUE;

        diameters = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            diameters[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(diameters);

        twoPointer();

        br.close();
    }

    private static void twoPointer() {

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int snowMan1 = diameters[i] + diameters[j];
                int start = 0;
                int end = n - 1;

                while (start < end) {

                    if (start == i || start == j) {
                        start++;
                        continue;
                    }

                    if (end == i || end == j) {
                        end--;
                        continue;
                    }

                    int snowMan2 = diameters[start] + diameters[end];
                    res = Math.min(res, Math.abs(snowMan2 - snowMan1));

                    if (snowMan1 < snowMan2) {
                        end--;
                    } else if (snowMan1 > snowMan2) {
                        start++;
                    } else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }

        System.out.println(res);
    }
}