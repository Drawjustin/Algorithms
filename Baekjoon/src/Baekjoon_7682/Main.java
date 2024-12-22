package Baekjoon_7682;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    private static int [] dx = {1,0,-1,0};
    private static int [] dy = {0,1,0,-1};
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {

        String command = "";
        char[][]map = new char[3][3];

        boolean isOk = true;
        while(true){
            command = br.readLine();
            if(command.equals("end"))
                break;

            isOk = true;
            init(command,map);
            isOk = finalCheck(isOk,check(map));


            if(isOk){
                wr.write("valid\n");
            }else{
                wr.write("invalid\n");
            }
        }
        wr.flush();

    }

    private static void init(String command, char[][] map) throws IOException {
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                map[i][j] = command.charAt(i*3 + j);
            }
        }


    }


    public static boolean finalCheck(boolean isOk, boolean result) {
        if (isOk) {
            return result;
        } else {
            return false;
        }
    }

    private static boolean check(char[][] map) {
        int xSize = 0;
        int oSize = 0;

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(map[i][j] == 'O') {
                    oSize++;
                }
                else if(map[i][j] == 'X'){
                    xSize++;
                }
            }
        }

        if(xSize < oSize){
            return false;
        }

        if(xSize - oSize >= 2){
            return false;
        }


        boolean oWin = false;
        boolean xWin = false;

        //가로검사
        for(int i=0; i<3; i++){
            boolean key = true;
            for(int j=0; j<2; j++){
                if(map[i][j] != map[i][j+1]){
                    key = false;
                }
            }
            if(key){
                if(map[i][0] == 'X'){
                    xWin = true;
                }
                else if(map[i][0] == 'O'){
                    oWin = true;
                }
            }
        }

        //세로검사
        for(int i=0; i<3; i++){
            boolean key = true;
            for(int j=0; j<2; j++){
                if(map[j][i] != map[j+1][i]){
                    key = false;
                }
            }
            if(key){
                if(map[0][i] == 'X'){
                    xWin = true;
                }
                else if(map[0][i] == 'O'){
                    oWin = true;
                }
            }
        }
        //우측 대각선검사
        boolean key = true;
        for(int i=0; i<2; i++){
            if(map[i][i] != map[i+1][i+1]){
                key = false;
            }
        }
        if(key){
            if(map[0][0] == 'X'){
                xWin = true;
            }
            else if(map[0][0] == 'O'){
                oWin = true;
            }
        }

        //좌측 대각선 검사
        key = true;
        for(int i=0; i<2; i++){
            if(map[i][2-i] != map[i+1][2-i-1]){
                key = false;
            }
        }
        if(key){
            if(map[2][0] == 'X'){
                xWin = true;
            }
            else if(map[2][0] == 'O'){
                oWin = true;
            }
        }


        //결과확인
        if(xWin && oWin){
            return false;
        }
        if(oWin){
            return xSize <= oSize;
        }
        if(xWin){
            return xSize > oSize;
        }

        if(!xWin && !oWin && oSize+xSize < 9){
            return false;
        }
        return true;
    }
}
