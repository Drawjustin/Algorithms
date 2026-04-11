package Baekjoon_8979;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Country {
        int id;
        int gold;
        int silver;
        int bronze;

        Country(int id, int gold, int silver, int bronze) {
            this.id = id;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Country[] countries = new Country[N];
        Country target = null;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());

            countries[i] = new Country(id, gold, silver, bronze);

            if (id == K) {
                target = countries[i];
            }
        }

        int rank = 1;

        for (Country c : countries) {
            if (isBetter(c, target)) {
                rank++;
            }
        }

        System.out.println(rank);
    }

    static boolean isBetter(Country a, Country b) {
        if (a.gold != b.gold) {
            return a.gold > b.gold;
        }
        if (a.silver != b.silver) {
            return a.silver > b.silver;
        }
        return a.bronze > b.bronze;
    }
}