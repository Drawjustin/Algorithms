package 레벨2.도넛과막대그래프;

import java.util.*;

public class 도넛과막대그래프 {
    public static void main(String[] args) {
        도넛과막대그래프 solution = new 도넛과막대그래프();
        
        // 테스트 케이스 1
        int[][] edges1 = {{2,3},{4,3},{1,1},{2,1}};
        System.out.println(Arrays.toString(solution.solution(edges1))); // [2, 1, 1, 0]
        
        // 테스트 케이스 2
        int[][] edges2 = {{4,11},{1,12},{8,3},{12,7},{4,2},{7,11},{4,8},{9,6},{10,11},{6,10},{3,5},{11,1},{5,3},{11,9},{3,8}};
        System.out.println(Arrays.toString(solution.solution(edges2))); // [4, 0, 1, 2]
    }
    
    /**
     * 핵심 아이디어: 진입 차수(in-degree)와 진출 차수(out-degree) 분석
     * 
     * 1. 생성 정점: 진입 차수 = 0, 진출 차수 >= 2
     * 2. 막대 그래프의 끝 정점: 진출 차수 = 0
     * 3. 8자 그래프의 중심 정점: 진입 차수 = 2, 진출 차수 = 2
     * 4. 도넛 그래프: 전체 - 막대 - 8자
     */
    public int[] solution(int[][] edges) {
        // 각 정점의 [진입 차수, 진출 차수]를 저장
        Map<Integer, int[]> degree = new HashMap<>();
        
        // 1. 모든 간선 정보를 순회하며 진입/진출 차수 계산
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            
            // from 정점의 진출 차수 증가
            degree.putIfAbsent(from, new int[2]);
            degree.get(from)[1]++;
            
            // to 정점의 진입 차수 증가
            degree.putIfAbsent(to, new int[2]);
            degree.get(to)[0]++;
        }
        
        // 결과: [생성 정점, 도넛, 막대, 8자]
        int createdVertex = 0;  // 생성한 정점
        int donutCount = 0;     // 도넛 그래프 개수
        int stickCount = 0;     // 막대 그래프 개수
        int eightCount = 0;     // 8자 그래프 개수
        
        // 2. 각 정점의 차수를 분석하여 그래프 유형 판단
        for (Map.Entry<Integer, int[]> entry : degree.entrySet()) {
            int vertex = entry.getKey();
            int inDegree = entry.getValue()[0];  // 진입 차수
            int outDegree = entry.getValue()[1]; // 진출 차수
            
            // 생성 정점: 진입 차수 = 0, 진출 차수 >= 2
            if (inDegree == 0 && outDegree >= 2) {
                createdVertex = vertex;
            }
            // 막대 그래프의 끝 정점: 진출 차수 = 0
            else if (outDegree == 0) {
                stickCount++;
            }
            // 8자 그래프의 중심 정점: 진입 차수 = 2, 진출 차수 = 2
            else if (inDegree >= 2 && outDegree == 2) {
                eightCount++;
            }
        }
        
        // 3. 도넛 그래프 개수 계산
        // 전체 그래프 수 = 생성 정점의 진출 차수
        int totalGraphs = degree.get(createdVertex)[1];
        donutCount = totalGraphs - stickCount - eightCount;
        
        return new int[]{createdVertex, donutCount, stickCount, eightCount};
    }
}
