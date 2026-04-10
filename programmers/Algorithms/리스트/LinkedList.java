package Algorithms.리스트;

import java.util.List;

public class LinkedList {


    class CustomLinkedList<T>{
        Node<T> head;
        Node<T> tail;
        int size;

        void add(T value){
            addIndex(value,size);
        }

        T get(int index) {
            return getNode(index).item;
        }
        void addIndex(T value, int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }

            Node<T> newNode = new Node<>(value);

            if (index == 0) {
                if (size == 0) {
                    head = newNode;
                    tail = newNode;
                } else {
                    newNode.next = head;
                    head.prev = newNode;
                    head = newNode;
                }
            } else if (index == size) {
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
            } else {
                Node<T> current = getNode(index); // index 위치의 기존 노드
                Node<T> prevNode = current.prev;

                prevNode.next = newNode;
                newNode.prev = prevNode;

                newNode.next = current;
                current.prev = newNode;
            }

            size++;
        }
        Node<T> getNode(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }

            if (index < size / 2) {
                Node<T> current = head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
                return current;
            } else {
                Node<T> current = tail;
                for (int i = size - 1; i > index; i--) {
                    current = current.prev;
                }
                return current;
            }
        }
        T removeIndex(int index){
            if(index < 0 || index >= size){
                throw new IndexOutOfBoundsException();
            }

            Node<T> removed;

            if(size == 1){
                removed = head;
                head = null;
                tail = null;
            } else if(index == 0){
                removed = head;
                head = head.next;
                head.prev = null;
            } else if(index == size - 1){
                removed = tail;
                tail = tail.prev;
                tail.next = null;
            } else {
                removed = getNode(index);
                Node<T> prevNode = removed.prev;
                Node<T> nextNode = removed.next;

                prevNode.next = nextNode;
                nextNode.prev = prevNode;
            }

            size--;
            return removed.item;
        }

        boolean isEmpty(){
            return size == 0;
        }
        void clear(){
            head = null;
            tail = null;
            size = 0;
        }

    }

    class Node<T> {
        T item;
        Node<T> prev;
        Node<T> next;

        Node(T item) {
            this.item = item;
        }
    }
}
