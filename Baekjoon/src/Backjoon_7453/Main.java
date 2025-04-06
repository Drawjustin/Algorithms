package Backjoon_7453;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Long[][] Array;
    static Long[] newArray1;
    static Long[] newArray2;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        Array = new Long[n][4];
        newArray1 = new Long[n];
        newArray2 = new Long[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                Array[i][j] = Long.parseLong(stk.nextToken());
            }
        }
        Map<Long,Long> map1 = new HashMap<>();
        Map<Long,Long> map2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Long start1 = Array[i][0];
            Long start3 = Array[i][2];
            for (int j = 0; j < n; j++) {
                Long start2 = Array[j][1];
                Long start4 = Array[j][3];
                Long newValue1 = start1 + start2;
                Long newValue2 = start3 + start4;
                newArray1[i] = newValue1;
                newArray2[i] = newValue2;
                if(map1.containsKey(newValue1)){
                    Long l = map1.get(newValue1);
                    map1.put(newValue1,l+1);
                }
                else{
                    map1.put(newValue1, 1L);
                }

                if(map2.containsKey(newValue2)){
                    Long l = map2.get(newValue2);
                    map2.put(newValue2,l+1);
                }
                else{
                    map2.put(newValue2, 1L);
                }
            }
        }

        Arrays.sort(newArray1);
        Arrays.sort(newArray2);

        int result = 0;
        for (int i = 0; i < n; i++) {
            Long AryValue = -newArray1[i];
            if(map2.containsKey(AryValue)){
                Long l = map2.get(AryValue);
                result += l;
            }

        }
        System.out.println(result);

    }

}
