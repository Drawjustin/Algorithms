package Baekjoon_3687;

import java.io.*;

public class Main {
    private static int [] dx = {1,0,-1,0};
    private static int [] dy = {0,1,0,-1};
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int [] ary = new int[9];
    public static void main(String[] args) throws IOException {
        ary[0] = 6;
        ary[1] = 2;
        ary[2] = 5;
        ary[3] = 5;
        ary[4] = 4;
        ary[5] = 5;
        ary[6] = 6;
        ary[7] = 3;
        ary[8] = 7;

        int testcase = Integer.parseInt(br.readLine());

        for(int i=0; i<testcase; i++){
            String maxValue = "";
            String minValue = "";
            int item = Integer.parseInt(br.readLine());

            maxValue = makeMax(item);
            minValue = makeMin(item);
            System.out.println(minValue+" "+maxValue);


        }

    }

    private static String makeMin(int item) {
        StringBuilder stb = new StringBuilder();
        int length = 1;
        int tempitem = item;
        while(true) {
            if (tempitem > 7) {
                length++;
                tempitem -= 7;
                
            }else{
                break;
            }
        }
        for (int i = 0; i < length; i++) {
            if(i == 0){
                for(int j = 1; j<= 8; j++){
                    if(item >= 2 && item <= 7){
                        if(ary[j] == item){
                            stb.append(j);
                            break;
                        }
                    }
                    else if(item - ary[j] <= (length-i-1) * 7 && item - ary[j] >= 2) {
                        stb.append(j);
                        item -= ary[j];
                        break;
                    }
                }
            }
            else{
                for(int j = 0; j<= 8; j++){
                    if(item >= 2 && item <= 7){
                        if(ary[j] == item){
                            stb.append(j);
                            break;
                        }
                    }
                    else if(item - ary[j] <= (length-i-1) * 7 && item - ary[j] >= 2) {
                        stb.append(j);
                        item -= ary[j];
                        break;
                    }
                }
            }
        }

        return stb.toString();
    }

    private static String makeMax(int item) {
        StringBuilder stb = new StringBuilder();
        int one = item / 2;
        for (int i = 0; i < one; i++) {
            stb.append(1);
        }
        if(item % 2 == 0)
            return stb.toString();
        else{
            stb.replace(0,1,"7");
        }
        return stb.toString();
    }
}
