package Baekjoon_7490;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder stb = new StringBuilder();
    private static List<String> list;
    private static char[] sign = {'+','-',' '};
    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine());

        for(int i=0; i<testCase; i++){
            list = new ArrayList<>();
            int N = Integer.parseInt(br.readLine());
            logic(N);
            System.out.println();
        }

    }

    private static void logic(int N) {
        dfs(0,N);
        Collections.sort(list);
        for (String s : list) {
            System.out.println(s);
        }
    }

    private static void dfs(int curIdx, int N) {
        if(curIdx == N){

            // 정규식 패턴: 연산 기호만 매칭
            Pattern pattern = Pattern.compile("[+-]");

            String stbResult = stb.toString().replace(" ", "");
            Matcher matcher = pattern.matcher(stbResult);
            List<String> operators = new ArrayList<>();
            while (matcher.find()) {
                operators.add(matcher.group());
            }
            String[] tokens = stbResult.split("[+-]");

            int result = Integer.parseInt(tokens[0]);
            for (int i = 1; i< tokens.length; i++) {
                String token = tokens[i];
                String oper = operators.get(i-1);
                if(oper.equals("+")){
                    result += Integer.parseInt(token);
                }
                else if(oper.equals("-")){
                    result -= Integer.parseInt(token);
                }
            }
            if(result == 0){
                list.add(stb.toString());
            }

            return;
        }

        stb.append(curIdx+1);
        if(curIdx + 1 != N) {
            for (int i = 0; i < 3; i++) {
                stb.append(sign[i]);
                dfs(curIdx + 1, N);
                stb.deleteCharAt(stb.length() - 1);
            }
        }else{
            dfs(curIdx + 1, N);
        }
        stb.deleteCharAt(stb.length()-1);

    }
}
