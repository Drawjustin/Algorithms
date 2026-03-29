package 레벨2.전화번호목록;

import java.util.*;

public class main {
    class Solution {
        public boolean solution(String[] phone_book) {
            boolean answer = true;
            boolean [] numberBook = new boolean[21];
            Arrays.sort(phone_book, Comparator.comparingInt(String::length));

            Set<String> set = new HashSet<>();
            List<Integer> slices = new ArrayList<>();
            for (int i = 0; i < phone_book.length; i++) {
                String curString = phone_book[i];
                if(!numberBook[curString.length()]){
                    numberBook[curString.length()] = true;
                    slices.add(curString.length());
                }

                for (int j = 0; j < slices.size(); j++) {
                    if(slices.get(j) > curString.length()){
                        break;
                    }
                    String substring = curString.substring(0, slices.get(j));

                    if (set.contains(substring)) {
                        answer = false;
                        break;
                    }
                }
                set.add(curString);

            }

            return answer;
        }
    }
}
