package Baekjoon_1863;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        int count = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            // 현재 높이보다 높은 건물들은 여기서 끝남
            while (!stack.isEmpty() && stack.peek() > height) {
                stack.pop();
                count++;
            }

            // 같은 높이는 이미 카운트된 건물이므로 무시
            if (!stack.isEmpty() && stack.peek() == height) {
                continue;
            }

            // 새로운 높이 추가
            if (height != 0) {
                stack.push(height);
            }
        }

        // 남아있는 건물들 처리
        count += stack.size();

        System.out.println(count);

    }
}
