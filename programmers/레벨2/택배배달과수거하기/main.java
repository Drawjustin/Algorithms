package 레벨2.택배배달과수거하기;

public class main {
    class Solution {
        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
            long answer = 0L;

            int deleveryCheckPoint = n - 1;
            int pickUpCheckPoint = n - 1;

            while (true) {
                // 1. 이번 턴에 방문할 '진짜 가장 먼 집' 찾기 (이미 0이 된 곳은 스킵)
                // 출발하기 전에 목표 지점을 확실히 정하는 과정입니다.
                while (deleveryCheckPoint >= 0 && deliveries[deleveryCheckPoint] == 0) {
                    deleveryCheckPoint--;
                }
                while (pickUpCheckPoint >= 0 && pickups[pickUpCheckPoint] == 0) {
                    pickUpCheckPoint--;
                }

                // 2. 둘 다 -1이면 모든 배달/수거가 완료되었으므로 종료
                if (deleveryCheckPoint == -1 && pickUpCheckPoint == -1) {
                    break;
                }

                // 3. 짐을 싣기 전, 이번 턴의 왕복 이동 거리를 먼저 정산
                answer += (Math.max(deleveryCheckPoint, pickUpCheckPoint) + 1) * 2L;

                // 4. 배달 진행 (목표 지점부터 역순으로 cap만큼)
                int curDeleveryBox = 0;
                for (int i = deleveryCheckPoint; i >= 0; i--) {
                    if (deliveries[i] > 0) {
                        int canDelivery = cap - curDeleveryBox;
                        if (deliveries[i] >= canDelivery) {
                            curDeleveryBox += canDelivery; // [수정됨] 싣을 수 있는 만큼 꽉 채움
                            deliveries[i] -= canDelivery;
                            break; // 트럭이 꽉 찼으므로 이번 턴 배달은 끝
                        } else {
                            curDeleveryBox += deliveries[i];
                            deliveries[i] = 0;
                        }
                    }
                }

                // 5. 수거 진행 (목표 지점부터 역순으로 cap만큼)
                int curPickupBox = 0;
                for (int i = pickUpCheckPoint; i >= 0; i--) {
                    if (pickups[i] > 0) {
                        int canPickup = cap - curPickupBox;
                        if (pickups[i] >= canPickup) {
                            curPickupBox += canPickup; // [수정됨] 수거할 수 있는 만큼 꽉 채움
                            pickups[i] -= canPickup;
                            break; // 트럭이 꽉 찼으므로 이번 턴 수거는 끝
                        } else {
                            curPickupBox += pickups[i];
                            pickups[i] = 0;
                        }
                    }
                }
            }

            return answer;
        }
    }
//    class Solution {
//        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
//            long answer = 0;
//
//            int deliverySum = 0;
//            int pickupSum = 0;
//
//            // 가장 먼 집부터 역순으로 탐색
//            for (int i = n - 1; i >= 0; i--) {
//                deliverySum += deliveries[i];
//                pickupSum += pickups[i];
//
//                // 배달해야 할 화물이나 수거해야 할 빈 상자가 있다면 (트럭이 와야 함)
//                while (deliverySum > 0 || pickupSum > 0) {
//                    // 트럭이 한 번 올 때마다 cap만큼 처리 가능
//                    deliverySum -= cap;
//                    pickupSum -= cap;
//
//                    // 이동 거리는 (인덱스 + 1)의 왕복이므로 * 2
//                    answer += (i + 1) * 2L;
//                }
//            }
//
//            return answer;
//        }
//    }
}
