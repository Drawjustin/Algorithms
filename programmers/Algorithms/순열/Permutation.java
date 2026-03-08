package Algorithms.순열;

import java.util.Arrays;

public class Permutation {
    static int [] arr = {1,2,3,4};
    static boolean[] visited = new boolean[4];
    static int[] output = new int [2];
    public static void main(String[] args) {
//        한정적인 재료에서 이름이 같고 순서가 같으면 중복된걸로 본다 - 일반 NP (순열)
//
//        한정적인 재료에서 이름이 같고 순서가 다르면 다른것으로 본다 - Index NP (순열)
//
//        한정적인 재료에서 이름만 같으면 순서가 달라도 중복된것으로 본다 - Index NP (조합)
        /**
         * [순열의 판단 기준 정의]
         *
         * 1. 일반 NP (Value-based)
         * - "값이 같으면 같은 놈이다."
         * - {1, 2(A), 2(B)} 에서 [1, 2(A), 2(B)]와 [1, 2(B), 2(A)]를 '똑같은 것'으로 간주.
         * - 용도: 중복된 결과값 없이 유니크한 조합/순열만 뽑고 싶을 때 사용.
         *
         * 2. 인덱스 NP (Index-based)
         * - "값은 같아도 태생(위치)이 다르면 다른 놈이다."
         * - {1, 2(A), 2(B)} 에서 [1, 2(A), 2(B)]와 [1, 2(B), 2(A)]를 '서로 다른 것'으로 간주.
         * - 용도: 모든 가능한 물리적 배치(Case)를 전부 다 확인해야 할 때 사용.
         */

        /**
         * [알고리즘의 황금률]
         * 1. "중복"이라는 단어가 붙으려면 -> 재료가 무한 리필(Reuse) 되어야 한다. (nHr, nPr)
         * 2. 재료가 한정되어 있다면 -> 결과에 똑같은 숫자가 나와도 그것은 "일반" 순열/조합이다.
         *
         * - Index NP는 "위치(인덱스)"를 재료로 쓰기 때문에 재료가 한정적이다.
         * - 따라서 Index NP로 만드는 것은 무조건 "일반" 순열/조합의 범주에 속한다.
         */


        /**
         * 기존의 Permutation (nPn, nPr)
         * 중복 순열 - visited 배열 삭제
         * 중복 X 순열(중복값 O) - 정렬 후 이전배열과 비교
         * 중복 X 순열(중복값 X) - visited 배열 사용
         */
            dfs(0, 2);

        /**
         * nextPermutation (nPn)
         * 중복 순열 - 기존의 Permutation (dfs 사용!!!!)
         * 중복 X 순열(중복값 O) - 그냥 사용
         * 중복 X 순열(중복값 X) - 그냥 사용
         * TODO 반드시 최초 배열이 오름차순 정렬이 되어있어야 함
         */
        do{
            System.out.println(Arrays.toString(arr));
        }while (nextPermutation(arr));


        /**
         * nextPermutation (nPr)
         *
         */
        do {
            // 앞의 r개만 출력 (이게 우리가 원하는 4P2 결과물)
            int[] output = Arrays.copyOfRange(arr, 0, 2);
            System.out.println(Arrays.toString(output));

            // ★ 핵심 꼼수: r번째 인덱스부터 맨 끝까지를 강제로 뒤집어버림 (내림차순 만들기)
            // 이렇게 하면 nextPermutation이 뒤쪽 탐색을 건너뛰고 앞자리를 바로 바꿔줌
            reverse(arr, 2, arr.length - 1);
        } while (nextPermutation(arr)); // 기본 nextPermutation 함수 (위의 2번과 동일)

    }
    // 특정 구간만 뒤집는 함수 (Swap 활용)
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }
    public static boolean nextPermutation(int[] ary) {
        int i = ary.length -1;
        while(i > 0 && ary[i - 1] >= ary[i]) i--;
        if(i <= 0) return false;

        int j = ary.length -1;
        while(ary[i - 1] >= ary[j]) j--;

        swap(ary, i-1, j);

        int k = ary.length -1;
        while (i < k) swap(ary, i++, k--);
        return true;
    }

    private static void swap(int[] ary, int a, int b) {
        int temp = ary[a];
        ary[a] = ary[b];
        ary[b] = temp;
    }

    // depth : 현재까지 뽑은 갯수, r : 총 뽑아야 하는 갯수
    static void dfs(int depth, int r){
        if (depth == r){
            System.out.println(Arrays.toString(output));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(visited[i]){

                continue;
            }
            // ★ 2. 중복 방지 마법의 if문 (가지치기)
            // 나랑 똑같이 생긴 앞의 숫자가 '방문 해제(false)' 상태라면? 나도 패스!
            if (i > 0 && arr[i] == arr[i - 1] && !visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            output[depth] = arr[i];
            dfs(depth+1, r);
            visited[i] = false;
        }

    }
}
