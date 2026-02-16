package 광물캐기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class main {
    class Solution {
        public int solution(int[] picks, String[] minerals) {
            int answer = 0;

            int diamondPick = picks[0];
            int ironPick = picks[1];
            int stonePick = picks[2];

            // 1. 전체 곡괭이로 캘 수 있는 최대 광물 수 계산
            int totalPick = Arrays.stream(picks).sum();
            int maxCanMine = totalPick * 5;

            // 2. 실제 가진 광물 수와 캘 수 있는 한계치 중 작은 값을 선택 (배열 범위 초과 방지)
            int limit = Math.min(minerals.length, maxCanMine);

            // 3. 배열 자르기
            String[] refinedMinerals = Arrays.copyOfRange(minerals, 0, limit);

            List<Minerals> refineMinerals2 = new ArrayList<>();

            for (int i = 0; i < refinedMinerals.length; i+=5) {
                int diamond = 0;
                int iron = 0;
                int stone = 0;
                int calCost = 0;
                for (int j = i; j < Math.min(i + 5, refinedMinerals.length); j++) {
                    switch (refinedMinerals[j]) {
                        case "diamond" : {
                            diamond += 1;
                            calCost += 25;
                            break;
                        }
                        case "iron" : {
                            iron += 1;
                            calCost += 5;
                            break;
                        }
                        case "stone" : {
                            stone += 1;
                            calCost += 1;
                            break;
                        }
                    }
                }

                refineMinerals2.add(new Minerals(diamond, iron, stone, calCost));
            }

            Collections.sort(refineMinerals2);


            for (Minerals minerals2 : refineMinerals2) {
                if (diamondPick > 0) {
                    diamondPick--;
                    answer += minerals2.diamond + minerals2.iron + minerals2.stone;
                } else if (ironPick > 0) {
                    ironPick--;
                    answer += minerals2.diamond * 5 + minerals2.iron + minerals2.stone;
                } else if (stonePick > 0) {
                    stonePick--;
                    answer += minerals2.diamond * 25 + minerals2.iron * 5 + minerals2.stone;
                } else {
                    break; // 곡괭이가 다 떨어지면 종료
                }
            }

            return answer;
        }
        static class Minerals implements Comparable<Minerals> {
            int diamond;
            int iron;
            int stone;
            int calCost;
            Minerals(int diamond, int iron, int stone, int calCost) {
                this.diamond = diamond;
                this.iron = iron;
                this.stone = stone;
                this.calCost = calCost;
            }

            public int compareTo(Minerals o) {
                return o.calCost - this.calCost;
            }
        }
    }
}
