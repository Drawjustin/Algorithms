package Algorithms.트리;

import java.util.ArrayDeque;
import java.util.Deque;

public class Tree {

    class CustomBinaryTree {
        Node root;

        void add(int value){
            if(root == null){
                Node node = new Node(value);
                root = node;
                return;
            }

            Node curNode = root;

            while (true) {
                if (value < curNode.item) {
                    if (curNode.left == null) {
                        curNode.left = new Node(value);
                        return;
                    }
                    curNode = curNode.left;
                } else {
                    if (curNode.right == null) {
                        curNode.right = new Node(value);
                        return;
                    }
                    curNode = curNode.right;
                }
            }
        }

        boolean contains(int value) {
            Node curNode = root;

            while (curNode != null) {
                if (value == curNode.item) {
                    return true;
                } else if (value < curNode.item) {
                    curNode = curNode.left;
                } else {
                    curNode = curNode.right;
                }
            }

            return false;
        }
        boolean remove(int value) {
            Node parent = null;
            Node current = root;

            // 1. 삭제할 노드 찾기
            while (current != null && current.item != value) {
                parent = current;

                if (value < current.item) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            // 못 찾았으면 false
            if (current == null) {
                return false;
            }

            // 2. 자식이 0개 또는 1개인 경우
            if (current.left == null || current.right == null) {
                Node child;

                if (current.left != null) {
                    child = current.left;
                } else {
                    child = current.right;
                }

                // 지우는 노드가 root인 경우
                if (parent == null) {
                    root = child;
                } else if (parent.left == current) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }

                return true;
            }

            // 3. 자식이 2개인 경우
            Node successorParent = current;
            Node successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            // successor 값을 current에 복사
            current.item = successor.item;

            // successor 제거
            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }

            return true;
        }

        void print() {
            printInOrder(root);
            System.out.println();
        }

        void printInOrder(Node node) {
            if (node == null) {
                return;
            }

            printInOrder(node.left);
            System.out.print(node.item + " ");
            printInOrder(node.right);
        }

        void printPreOrder(Node node){
            if(node == null){
                return;
            }
            System.out.println(node.item + " ");
            printPreOrder(node.left);
            printPreOrder(node.right);
        }

        void printPostOrder(Node node){
            if(node == null){
                return;
            }
            printPostOrder(node.left);
            printPostOrder(node.right);
            System.out.println(node.item + " ");
        }

        void printLevelOrder(){
            if(root == null){
                return;
            }
            Deque<Node> queue = new ArrayDeque<>();
            queue.offer(root);
            while(!queue.isEmpty()){
                Node curNode = queue.poll();
                System.out.println(curNode.item + " ");
                if(curNode.left != null)
                    queue.offer(curNode.left);
                if(curNode.right != null)
                    queue.offer(curNode.right);
            }
        }

        int min(){
            if(root == null){
                throw new IllegalStateException();
            }
            Node curNode = root;

            while(curNode.left != null){
                curNode = curNode.left;
            }
            return curNode.item;
        }

        int max(){
            if(root == null){
                throw new IllegalStateException();
            }
            Node curNode = root;

            while(curNode.right != null){
                curNode = curNode.right;
            }
            return curNode.item;
        }

        int height(Node node){
            if(node == null){
                return -1;
            }
//            빈 트리 높이 = -1
//            리프 노드 높이 = 0
            return Math.max(height(node.left), height(node.right)) + 1;
        }

        int count(Node node){
            if(node == null){
                return 0;
            }

            return count(node.left) + count(node.right) + 1;
        }
        boolean validate() { return validate(root, Long.MIN_VALUE, Long.MAX_VALUE); }
        boolean validate(Node node, long min, long max) {
            if (node == null) {
                return true;
            }

            if (node.item < min || node.item >= max) {
                return false;
            }

            return validate(node.left, min, node.item)
                    && validate(node.right, node.item, max);
        }

        class Node {
            int item;
            Node left;
            Node right;

            Node(int item) {
                this.item = item;
            }
        }
    }


    class BinaryTree {
        Node root;

        void add(int value){
            root = insert(root, value);
        }

        Node insert(Node node, int value){
            if(node == null){
                return new Node(value);
            }

            if(value < node.item){
                node.left = insert(node.left, value);
            } else {
                node.right = insert(node.right, value);
            }

            return rebalance(node);
        }

        Node rebalance(Node node){
            if(node == null){
                return null;
            }

            updateHeight(node);
            int balance = getBalance(node);

            // LL / LR
            if(balance > 1){
                if(getBalance(node.left) < 0){
                    node.left = rotateLeft(node.left); // LR
                }
                return rotateRight(node); // LL 또는 LR 마무리
            }

            // RR / RL
            if(balance < -1){
                if(getBalance(node.right) > 0){
                    node.right = rotateRight(node.right); // RL
                }
                return rotateLeft(node); // RR 또는 RL 마무리
            }

            return node;
        }
        Node rotateLeft(Node node){
            Node child = node.right;
            Node childLeft = child.left;

            child.left = node;
            node.right = childLeft;

            updateHeight(node);
            updateHeight(child);

            return child;
        }

        Node rotateRight(Node node){
            Node child = node.left;
            Node childRight = child.right;

            child.right = node;
            node.left = childRight;

            updateHeight(node);
            updateHeight(child);

            return child;
        }

        int getBalance(Node node) {
            if (node == null) {
                return 0;
            }
            return height(node.left) - height(node.right);
        }

        int height() {
            return height(root);
        }

        int height(Node node) {
            if (node == null) {
                return -1;
            }
            return node.height;
        }

        void updateHeight(Node node) {
            if (node == null) {
                return;
            }
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
        class Node {
            int item;
            int height;
            Node left;
            Node right;

            Node(int item){
                this.item = item;
                this.height = 0;
            }
        }
    }





}