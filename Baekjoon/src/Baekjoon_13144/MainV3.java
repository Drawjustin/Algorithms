package Baekjoon_13144;

import java.util.Scanner;
import java.util.Stack;

public class MainV3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String bomb = sc.next();
        int len = bomb.length();
        int idx = -1;
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<str.length(); i++) {
            stack.push(str.charAt(i));
            idx++;

            while(!stack.isEmpty() && stack.peek() == bomb.charAt(len-1)) {
                boolean flag = true;
                for(int j=0; j<len; j++) {
                    if(stack.get(idx-j) != bomb.charAt(len-1-j)) {
                        flag = false;
                        break;
                    }
                }

                if(flag) {
                    for(int j=0; j<len; j++) {
                        stack.pop();
                        idx--;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        if(sb.length() == 0) System.out.print("FRULA");
        else System.out.print(sb.toString());
    }
}
