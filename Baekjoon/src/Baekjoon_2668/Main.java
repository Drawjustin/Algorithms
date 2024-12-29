package Baekjoon_2668;

import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

    static int [] ary;
    static boolean [] isVisited;
    static List<Integer> result = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        int size = Integer.parseInt(br.readLine());

        ary = new int[size+1];
        isVisited = new boolean[size+1];

        for(int i=1; i<=size; i++){
            ary[i] = Integer.parseInt(br.readLine());
        }

        for(int i=1; i<=size; i++){
            if(!isVisited[i]){
                logic(i);
            }

        }

        Collections.sort(result);

        System.out.println(result.size());
        for (Integer i : result) {
            System.out.println(i);
        }


    }

    private static void logic(int i) {
        Deque<Integer> deque = new LinkedList<>();

        isVisited[i] = true;
        deque.add(i);
        int target = ary[i];
        while(!isVisited[target]){
            isVisited[target] = true;
            deque.add(target);
            target = ary[target];
        }


        while (!deque.isEmpty()){
            int curIndex = deque.poll();
            if(target == curIndex){
                result.add(curIndex);
                while(!deque.isEmpty()){
                    result.add(deque.poll());
                }
            }
        }



    }
}
