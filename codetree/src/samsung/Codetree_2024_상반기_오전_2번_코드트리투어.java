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
    static boolean[] deletedItems = new boolean[30001]; // 배열로 변경 (최대 ID가 30,000)
    static int[][] allPairsDistances;
    static boolean[] computedSources;
    static PriorityQueue<Item> itemList = new PriorityQueue<>(new Comparator<Item>(){
        @Override
        public int compare(Item i1, Item i2){
            int diff1 = i1.value - i1.cost;
            int diff2 = i2.value - i2.cost;

            if(diff1 == diff2){
                return i1.id - i2.id;
            }
            return diff1 > diff2 ? -1 : 1;
        }
    });

    static StringBuilder sb = new StringBuilder(); // 출력 버퍼링

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 1; i < Q; i++) {
            logic();
        }

        System.out.print(sb); // 한 번에 출력
    }

    private static void logic() throws IOException{
        stk = new StringTokenizer(br.readLine());
        status = Integer.parseInt(stk.nextToken());
        switch(status){
            case 200 :
                int id = Integer.parseInt(stk.nextToken());
                int value = Integer.parseInt(stk.nextToken());
                int destination = Integer.parseInt(stk.nextToken());
                ensureDistanceComputed(start);
                int cost = allPairsDistances[start][destination];
                deletedItems[id] = false; // HashSet 대신 배열 사용
                itemList.add(new Item(id, value, destination, cost));
                break;
            case 300 :
                int deletedId = Integer.parseInt(stk.nextToken());
                deletedItems[deletedId] = true; // HashSet 대신 배열 사용
                break;
            case 400 :
                if(!itemList.isEmpty()){
                    // 삭제된 아이템을 처리
                    while(!itemList.isEmpty() && deletedItems[itemList.peek().id]){
                        itemList.poll();
                    }
                    if(itemList.isEmpty() || itemList.peek().cost == Integer.MAX_VALUE || itemList.peek().value - itemList.peek().cost < 0){
                        sb.append("-1\n");
                    }else{
                        Item curItem = itemList.poll();
                        sb.append(curItem.id).append("\n");
                    }
                } else {
                    sb.append("-1\n");
                }
                break;
            case 500 :
                start = Integer.parseInt(stk.nextToken());
                ensureDistanceComputed(start);
                List<Item> tempList = new ArrayList<>(itemList);
                itemList.clear();
                for (Item item : tempList) {
                    if(!deletedItems[item.id]) {
                        item.cost = allPairsDistances[start][item.destination];
                        itemList.add(item);
                    }
                }
                break;
        }
    }
    private static void ensureDistanceComputed(int source) {
        if (!computedSources[source]) {
            calculateAllDistances(source, allPairsDistances[source]);
            computedSources[source] = true;
        }
    }
    private static void precomputeAllDistances() {
        allPairsDistances = new int[N][N];

        // 각 노드를 출발지로 하여 다익스트라 알고리즘 실행
        for (int source = 0; source < N; source++) {
            calculateAllDistances(source, allPairsDistances[source]);
        }
    }

    private static void calculateAllDistances(int start, int[] distances) {
        // 거리 배열 초기화
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        // 다익스트라 알고리즘
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distance));
        pq.offer(new Pair(start, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int currentNode = current.node;
            int currentDist = current.distance;

            // 이미 처리된 노드라면 스킵
            if (currentDist > distances[currentNode]) continue;

            // 인접 노드 탐색
            for (Node node : nodeList.get(currentNode)) {
                int nextNode = node.ui;
                int weight = node.wi;

                int newDist = distances[currentNode] + weight;
                if (newDist < distances[nextNode]) {
                    distances[nextNode] = newDist;
                    pq.offer(new Pair(nextNode, newDist));
                }
            }
        }
    }

    private static void init() throws IOException{
        Q = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine());
        status = Integer.parseInt(stk.nextToken());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < N; i++) {
            nodeList.add(new ArrayList<>());
        }

        computedSources = new boolean[N];
        allPairsDistances = new int[N][N];

        if(status == 100){
            for (int i = 0; i < M; i++) {
                int vi = Integer.parseInt(stk.nextToken());
                int ui = Integer.parseInt(stk.nextToken());
                int wi = Integer.parseInt(stk.nextToken());

                nodeList.get(vi).add(new Node(ui, wi)); // 필요한 정보만 저장
                nodeList.get(ui).add(new Node(vi, wi));
            }
        }
        calculateAllDistances(start, allPairsDistances[start]);
        computedSources[start] = true;
    }

    // 노드 클래스 간소화
    static class Node{
        int ui;
        int wi;

        public Node(int ui, int wi) {
            this.ui = ui;
            this.wi = wi;
        }
    }

    // 다익스트라용 페어 클래스
    static class Pair {
        int node;
        int distance;

        public Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
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