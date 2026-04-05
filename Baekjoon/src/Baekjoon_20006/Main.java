package Baekjoon_20006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(stk.nextToken());
        int m =  Integer.parseInt(stk.nextToken());
        List<Room> list = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            stk = new  StringTokenizer(br.readLine());
            int level =  Integer.parseInt(stk.nextToken());
            String name =  stk.nextToken();
            boolean isJoin = false;
            for (int j = 0; j < list.size(); j++) {
                Room curRoom = list.get(j);
                if(curRoom.memberList.size() < m && curRoom.minLevel <= level && curRoom.maxLevel >= level){
                    isJoin = true;
                    curRoom.memberList.add(new Member(level,name));
                    break;
                }
            }
            if(!isJoin){
                Room room = new Room(new ArrayList<>(), level - 10, level + 10);
                room.memberList.add(new Member(level,name));
                list.add(room);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            Room curRoom = list.get(i);
            curRoom.memberList.sort((Member o1, Member o2) -> o1.name.compareTo(o2.name));

            if(curRoom.memberList.size() == m){
                System.out.println("Started!");
            }else{
                System.out.println("Waiting!");
            }
            for(Member member : curRoom.memberList){
                System.out.println(member.level + " " +  member.name);
            }
        }



    }

    static class Room{
        int minLevel;
        int maxLevel;
        List<Member> memberList;
        public Room(List<Member> memberList, int minLevel, int maxLevel) {
            this.memberList = memberList;
            this.minLevel = minLevel;
            this.maxLevel = maxLevel;
        }
    }
    static class Member {
        int level;
        String name;
        public Member(int level, String  name) {
            this.level = level;
            this.name = name;
        }
    }
}
