package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Codetree_2024_상반기_오전_2번_코드트리투어 {
    static List<List<Node>> nodeList = new ArrayList<>();
    static int Q,N,M,status;
    static int start = 0;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk;
    static Set<Integer> deletedItemSet = new HashSet<>();
    static PriorityQueue<Item> itemList = new PriorityQueue<>(new Comparator<Item>(){
        @Override
        public int compare(Item i1, Item i2){
            int diff1 = i1.value - i1.cost;
            int diff2 = i2.value - i2.cost;

            if(diff1  == diff2){
                return i1.id - i2.id;
            }
            return diff1 < diff2 ? -1 : 1;
        }

    });
    public static void main(String[] args) throws IOException {
        init();

        for (int i = 1; i < Q; i++) {
            logic();
        }
    }

    private static void logic() throws IOException{
        stk = new StringTokenizer(br.readLine());
        status = Integer.parseInt(stk.nextToken());
        switch(status){
            case 200 :
                int id = Integer.parseInt(stk.nextToken());
                int value = Integer.parseInt(stk.nextToken());
                int destination = Integer.parseInt(stk.nextToken());
                int cost = bfs(start,destination);
                itemList.add(new Item(id,value,destination,cost));
                break;
            case 300 :
                int deletedId = Integer.parseInt(stk.nextToken());
                deletedItemSet.add(deletedId);
                break;
            case 400 :
                if(!itemList.isEmpty()){
                    while(deletedItemSet.contains(itemList.peek().id)){
                        itemList.poll();
                    }
                    if(itemList.peek().cost == 999){
                        System.out.println(-1);
                        break;
                    }else{
                        Item curItem = itemList.poll();
                        System.out.println(curItem.id);
                    }
                }
                break;
            case 500 :
                start = Integer.parseInt(stk.nextToken());
                List<Item> tempList = new ArrayList<>(itemList);
                itemList.clear();
                for (Item item : tempList) {
                    if(deletedItemSet.contains(item.id)){
                        continue;
                    }
                    item.cost = bfs(start,item.destination);
                    itemList.add(item);
                }
                break;
            default:
                throw new RuntimeException();
        }

    }

    private static void init() throws IOException{
        Q = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine());
        status = Integer.parseInt(stk.nextToken());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        // 노드 초기화
        for (int i = 0; i < N; i++) {
            nodeList.add(new ArrayList<>());
        }


        if(status == 100){
            for (int i = 0; i < M; i++) {
                int vi = Integer.parseInt(stk.nextToken());
                int ui = Integer.parseInt(stk.nextToken());
                int wi = Integer.parseInt(stk.nextToken());

                nodeList.get(vi).add(new Node(vi,ui,wi));
                nodeList.get(ui).add(new Node(ui,vi,wi));
            }
        }
    }
    static class Node{
        int vi;
        int ui;
        int wi;

        public Node(int vi, int ui, int wi) {
            this.vi = vi;
            this.ui = ui;
            this.wi = wi;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "vi=" + vi +
                    ", ui=" + ui +
                    ", wi=" + wi +
                    '}';
        }
    }
    static class Item{
        int id;
        int value;
        int destination;
        int cost;

        public Item(int id, int value, int destination, int cost) {
            this.id = id;
            this.value = value;
            this.destination = destination;
            this.cost = cost;
        }
    }
}
