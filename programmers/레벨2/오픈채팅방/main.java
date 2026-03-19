package 레벨2.오픈채팅방;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {
    class Solution {
        public String[] solution(String[] record) {
            String[] answer = {};
            Map<String, String> map = new HashMap<>();
            List<String[]> list = new ArrayList<>();
            for (int i = 0; i < record.length; i++) {
                String[] s = record[i].split(" ");
                String order = s[0];
                String id = s[1];

                switch (order) {
                    case "Enter":{
                        String name = s[2];
                        map.put(id, name);
                        list.add(new String[]{id,"E"});
                        break;
                    }
                    case "Leave": {
                        list.add(new String[]{id,"L"});
                        break;
                    }
                    case "Change": {
                        String name = s[2];
                        map.put(id, name);
                        break;
                    }
                }
            }
            List<String> result = new ArrayList<>();

            for(String [] s : list){
                String id = s[0];
                String order = s[1];
                String name = map.getOrDefault(id, "알수없음");
                String orderTrans = order.equals("E") ? "님이 들어왔습니다." : "님이 나갔습니다.";
                result.add(name + orderTrans);
            }
            map.merge()
            answer = result.toArray(new String[0]);
            return answer;
        }
    }
}
