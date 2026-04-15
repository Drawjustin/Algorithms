package 레벨2.바이러스파이프;

import java.util.ArrayList;
import java.util.List;

public class Main {
    class Solution {
        static int maxValue;
        static Node root;
        static List<Integer> APYPE;
        static List<Integer> BPYPE;
        static List<Integer> CPYPE;
        static List<Node> list;

        public int solution(int n, int infection, int[][] edges, int k) {
            maxValue = 0;
            list = new ArrayList<>();
            APYPE = new ArrayList<>();
            BPYPE = new ArrayList<>();
            CPYPE = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                list.add(new Node(new ArrayList<>(), false));
            }

            for (int i = 0; i < edges.length; i++) {
                int start = edges[i][0];
                int end = edges[i][1];
                int pipeType = edges[i][2];

                list.get(start).list.add(new int[]{end, pipeType});
                list.get(end).list.add(new int[]{start, pipeType});
            }

            root = list.get(infection);
            root.infection = true;

            for (int[] edge : root.list) {
                int pipeType = edge[1];
                switch (pipeType) {
                    case 1:
                        APYPE.add(edge[0]);
                        break;
                    case 2:
                        BPYPE.add(edge[0]);
                        break;
                    case 3:
                        CPYPE.add(edge[0]);
                        break;
                }
            }

            int[] orderAryList = new int[k + 1];
            comb(orderAryList, 1, k, n, APYPE, BPYPE, CPYPE);

            return maxValue;
        }

        private void comb(int[] orderAryList, int curIndex, int k, int n,
                          List<Integer> aType, List<Integer> bType, List<Integer> cType) {
            if (maxValue == n) {
                return;
            }

            if (curIndex > k) {
                int max = 1;

                List<Integer> aCopy = new ArrayList<>(aType);
                List<Integer> bCopy = new ArrayList<>(bType);
                List<Integer> cCopy = new ArrayList<>(cType);

                boolean[] infectedSnapshot = new boolean[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    infectedSnapshot[i] = list.get(i).infection;
                }

                for (int i = 1; i <= k; i++) {
                    if (max == n) {
                        maxValue = n;
                        restoreInfection(infectedSnapshot);
                        return;
                    }

                    int openPipe = orderAryList[i];

                    switch (openPipe) {
                        case 1: {
                            for (int nextNode : new ArrayList<>(aCopy)) {
                                Node node = list.get(nextNode);
                                if (node.infection) {
                                    continue;
                                }
                                node.infection = true;
                                max++;

                                for (int[] nextNodeList : node.list) {
                                    Node nextNextNode = list.get(nextNodeList[0]);
                                    if (nextNextNode.infection) {
                                        continue;
                                    }

                                    int pipeType = nextNodeList[1];
                                    switch (pipeType) {
                                        case 1:
                                            aCopy.add(nextNodeList[0]);
                                            break;
                                        case 2:
                                            bCopy.add(nextNodeList[0]);
                                            break;
                                        case 3:
                                            cCopy.add(nextNodeList[0]);
                                            break;
                                    }
                                }
                            }
                            break;
                        }
                        case 2: {
                            for (int nextNode : new ArrayList<>(bCopy)) {
                                Node node = list.get(nextNode);
                                if (node.infection) {
                                    continue;
                                }
                                node.infection = true;
                                max++;

                                for (int[] nextNodeList : node.list) {
                                    Node nextNextNode = list.get(nextNodeList[0]);
                                    if (nextNextNode.infection) {
                                        continue;
                                    }

                                    int pipeType = nextNodeList[1];
                                    switch (pipeType) {
                                        case 1:
                                            aCopy.add(nextNodeList[0]);
                                            break;
                                        case 2:
                                            bCopy.add(nextNodeList[0]);
                                            break;
                                        case 3:
                                            cCopy.add(nextNodeList[0]);
                                            break;
                                    }
                                }
                            }
                            break;
                        }
                        case 3: {
                            for (int nextNode : new ArrayList<>(cCopy)) {
                                Node node = list.get(nextNode);
                                if (node.infection) {
                                    continue;
                                }
                                node.infection = true;
                                max++;

                                for (int[] nextNodeList : node.list) {
                                    Node nextNextNode = list.get(nextNodeList[0]);
                                    if (nextNextNode.infection) {
                                        continue;
                                    }

                                    int pipeType = nextNodeList[1];
                                    switch (pipeType) {
                                        case 1:
                                            aCopy.add(nextNodeList[0]);
                                            break;
                                        case 2:
                                            bCopy.add(nextNodeList[0]);
                                            break;
                                        case 3:
                                            cCopy.add(nextNodeList[0]);
                                            break;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }

                maxValue = Math.max(max, maxValue);
                restoreInfection(infectedSnapshot);
                return;
            }

            for (int j = 1; j <= 3; j++) {
                orderAryList[curIndex] = j;
                comb(orderAryList, curIndex + 1, k, n, aType, bType, cType);
            }
        }

        private void restoreInfection(boolean[] infectedSnapshot) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).infection = infectedSnapshot[i];
            }
        }

        class Node {
            List<int[]> list;
            boolean infection;

            Node(List<int[]> list, boolean infection) {
                this.list = list;
                this.infection = infection;
            }
        }
    }
}
