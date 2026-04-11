package Baekjoon_25757;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        char game = st.nextToken().charAt(0);

        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        int people = set.size();
        int need = 0;

        if (game == 'Y') {
            need = 1;
        } else if (game == 'F') {
            need = 2;
        } else if (game == 'O') {
            need = 3;
        }

        System.out.println(people / need);
    }
}
