package SWEA_병사관리;

class UserSolution
{
    Map<Integer,Integer>
    public static class Node{
        Soldier data;
        Node next;

        public Node(Soldier data){
            this.data = data;
        }

    }
    public static class Soldier{
        //병사들은 각각 고유번호, 소속팀, 평판 점수를 가지고 있다.
        int idx;
        int team;
        int score;
    }
    public void init()
    {

    }

    public void hire(int mID, int mTeam, int mScore)
    {

    }

    public void fire(int mID)
    {

    }

    public void updateSoldier(int mID, int mScore)
    {

    }

    public void updateTeam(int mTeam, int mChangeScore)
    {

    }

    public int bestSoldier(int mTeam)
    {

        return 0;
    }
}