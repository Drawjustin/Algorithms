package SWEA_병사관리;

class UserSolution
{
    public class Team {
        Node[] nodes = new Node[6];
    }
    public Team[] t;
    public int[] mIDAry;
    public int[] mScoreAry;
    public int[] VersionAry;
    public static class Node{
        Solider data;
        Node next;
        public Node(Solider data){
            this.data = data;
            next = null;
        }

    }
    public static class Solider{
        //병사들은 각각 고유번호, 소속팀, 평판 점수를 가지고 있다.
        int idx;
        int team;
        int version;

        public Solider(int idx, int team, int version){
            this.idx = idx;
            this.team = team;
            this.version = version;
        }
    }


    Node getNewNode(Solider solider){
        return new Node(solider);
    }


    public void init()
    {
        t = new Team[6];
        mIDAry = new int [100001];
        mScoreAry = new int [100001];
        VersionAry = new int [100001];
    }

    public void hire(int mID, int mTeam, int mScore)
    {
        t[mTeam].nodes[mTeam].next = new Node(new Solider(mID,mTeam,1));
        mIDAry[mID] = 1;
        mScoreAry[mID] = mScore;
    }

    public void fire(int mID)
    {
        mIDAry[mID] = 0;
        VersionAry[mID] = 0;
    }

    public void updateSoldier(int mID, int mScore)
    {
        mScoreAry[mID] = mScore;
        VersionAry[mID]++;
    }

    public void updateTeam(int mTeam, int mChangeScore)
    {
        Node curNode = t[mTeam].nodes[mTeam];
        while(curNode != null){
            if(mIDAry[curNode.data.idx] == 1){
                if(mScoreAry[curNode.data.idx] + mChangeScore >5){
                    mScoreAry[curNode.data.idx] = 5;
                }
                else if(mScoreAry[curNode.data.idx] + mChangeScore < 1){
                    mScoreAry[curNode.data.idx] = 1;
                }
                else{
                    mScoreAry[curNode.data.idx] += mChangeScore;
                }
            }
            curNode = curNode.next;
        }


    }

    public int bestSoldier(int mTeam)
    {
        int MaxIdx = 0;
        int MaxScore = 0;

        Node curNode = t[mTeam].nodes[mTeam];

        while(curNode != null){
            if(mIDAry[curNode.data.idx] == 1) {
                if (MaxScore > mScoreAry[curNode.data.idx]) {
                    MaxScore = mScoreAry[curNode.data.idx];
                    MaxIdx = curNode.data.idx;
                } else {
                    MaxIdx = Math.max(curNode.data.idx, MaxIdx);
                }

            }
            curNode = curNode.next;
        }
        return MaxIdx;
    }
}