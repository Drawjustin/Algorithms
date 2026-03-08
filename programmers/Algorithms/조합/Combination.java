package Algorithms.조합;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Combination {
    static int[] arr = {1, 2, 3, 4};
    static int[] output = new int[2]; // 4C2 (4개 중 2개 뽑기)

    public static void main(String[] args) {
        /**
         * [조합(Combination) 알고리즘 유형별 최적 기법]
         *
         * 1. 중복 원소가 있는 배열 -> 중복 없는 결과 추출
         * - 예시: {사과, 배, 배} 중 2개 고르기 -> [사과, 배], [배, 배]
         * - 최적: DFS + 정렬 + 가지치기
         * - 핵심: if(i > start && arr[i] == arr[i-1]) continue;
         * - 비고: Index NP 사용 시 [사과, 배(A)], [사과, 배(B)]로 결과값 중복 발생하여 비추천.
         *
         * 2. 중복 원소가 없는 배열 -> 중복 없는 결과 추출
         * - 예시: {1, 2, 3, 4, 5} 중 대표 3명 뽑기 (5C3)
         * - 최적: Index NP (0/1 플래그 배열 활용)
         * - 핵심: p[] 배열을 00111(N-R개만큼 0, R개만큼 1)로 초기화 후 np(p) 호출.
         * - 장점: 반복문 기반이라 N이 클 때 DFS보다 속도가 빠르고 메모리가 효율적임.
         *
         * 3. 중복 조합 (원소 중복 선택 가능)
         * - 예시: 3종류 메뉴(A, B, C) 중 중복 허용하여 5개 고르기 (3H5)
         * - 최적: DFS
         * - 핵심: dfs(depth + 1, i); // 다음 재귀에 현재 인덱스 i를 그대로 전달.
         * - 비고: R이 N보다 클 수 있으며, Index NP로는 구조상 구현 불가능.
         */
        System.out.println("--- 조합 (4C2) ---");
        combine(0, 0);

        int [] indexNP = {0,0,1,1};
        do {
            for(int i=0; i<indexNP.length; ++i) {
                if(indexNP[i]==1) System.out.print(arr[i]+" ");
            }
            System.out.println();
        }while (nextPermutation(indexNP));


    }

    static boolean nextPermutation(int[] ary) {
        // 1,3,5,4,2
        int i = ary.length -1;

        while(i > 0 && ary[i-1] >= ary[i]) i--;

        if(i <= 0) return false;

        int j =  ary.length -1;
        while(ary[i - 1] >= ary[j]) j--;

        swap(ary, i - 1, j);

        int k = ary.length -1;
        while(i < k) swap(ary, i++, k--);

        return true;
    }
    static void  swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // depth: 몇 개 뽑았나, start: 어디서부터 검사하나
    static void combine(int depth, int start) {
        if (depth == 2) { // 2개를 다 뽑았다면
            System.out.println(Arrays.toString(output));
            return;
        }
//            // 현재 자리(depth)에서 사용한 값들을 기록할 수첩
//        Set<Integer> usedInThisDepth = new HashSet<>();

        for (int i = start; i < arr.length; i++) {

//            // 이미 이 자리(depth)에서 이 '값'을 뽑아서 결과를 다 확인했다면? 패스!
//            if (usedInThisDepth.contains(arr[i])) continue;
//
//            // 수첩에 기록
//            usedInThisDepth.add(arr[i]);

            // ★ 마법의 if문: 현재 루프에서 '이전의 나'와 똑같은 숫자가 나왔다면 건너뜁니다.
            // i > start 조건이 핵심입니다! (이번 턴의 첫 번째 선택은 허용하되, 그 다음 똑같은 놈은 컷!)
            if (i > start && arr[i] == arr[i - 1]) continue;

            output[depth] = arr[i];
            // 중요! 다음 재귀 때는 현재 뽑은 'i'의 다음 번호(i + 1)를 시작점으로 넘김
            combine(depth + 1, i + 1);
        }
    }
}
