package Baekjoon_9655;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        if (n % 2 == 1) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }

    }
}