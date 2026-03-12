package 레벨2.튜플;

import java.util.*;

public class main {
    class Solution {
        public int[] solution(String s) {
            List<List<Integer>> list = new ArrayList<>();

            for (int i = 1; i < s.length() - 1; i++) {
                if (s.charAt(i) == '{') {
                    List<Integer> temp = new ArrayList<>();
                    StringBuilder stb = new StringBuilder();

                    while (s.charAt(i) != '}') {
                        if (s.charAt(i) == ',') {
                            temp.add(Integer.parseInt(stb.toString()));
                            stb = new StringBuilder();
                        } else if (s.charAt(i) != '{') {
                            stb.append(s.charAt(i));
                        }
                        i++;
                    }

                    // 마지막 숫자 추가
                    temp.add(Integer.parseInt(stb.toString()));
                    list.add(temp);
                }
            }

            list.sort(Comparator.comparingInt(List::size));

            Set<Integer> set = new HashSet<>();
            List<Integer> ans = new ArrayList<>();

            for (List<Integer> l1 : list) {
                for (int num : l1) {
                    if (!set.contains(num)) {
                        set.add(num);
                        ans.add(num);
                    }
                }
            }

            return ans.stream().mapToInt(i -> i).toArray();
        }
    }
}
