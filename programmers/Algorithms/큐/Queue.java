package Algorithms.큐;

public class Queue {

    class CustomQueue {
        int[] queue;
        int head;
        int tail;
        int size;

        CustomQueue(int capacity) {
            queue = new int[Math.max(1, capacity)];
            head = 0;
            tail = 0;
            size = 0;
        }

        void offer(int value) {
            if (isFull()) {
                int[] newQueue = new int[queue.length * 2];
                for (int i = 0; i < size; i++) {
                    newQueue[i] = queue[(head + i) % queue.length];
                }
                queue = newQueue;
                head = 0;
                tail = size;
            }

            queue[tail] = value;
            tail = (tail + 1) % queue.length;
            size++;
        }

        int poll() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }

            int value = queue[head];
            head = (head + 1) % queue.length;
            size--;
            return value;
        }

        int peek() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            return queue[head];
        }

        boolean isFull() {
            return size == queue.length;
        }

        boolean isEmpty() {
            return size == 0;
        }

        int size() {
            return size;
        }

        void clear() {
            head = 0;
            tail = 0;
            size = 0;
        };
    }
    class GenericCustomQueue<T> {
        Object [] queue;
        int head;
        int tail;
        int size;

        GenericCustomQueue(int capacity){
            queue = new Object[Math.max(1, capacity)];
        }

        void offer(T value){
            if(isFull()){
                Object [] newQueue = new Object[queue.length * 2];
                for (int i = 0; i < size; i++) {
                    newQueue[i] = queue[(head + i) % queue.length];
                }
                queue = newQueue;
                head = 0;
                tail = size;
            }
            queue[tail] = value;
            tail = (tail + 1) % queue.length;
            size++;
        }

        T poll(){
            if(isEmpty()){
                throw new IllegalStateException();
            }

            T v = elementAt(head);
            queue[head] = null;
            head = (head + 1) % queue.length;
            size--;
            return v;
        }

        T peek(){
            if(isEmpty()){
                throw new IllegalStateException();
            }
            return elementAt(head);
        }

        int size(){
            return size;
        }

        boolean isEmpty(){
            return size == 0;
        }

        boolean isFull(){
            return size == queue.length;
        }

        void clear(){
            for (int i = 0; i < size; i++) {
                queue[(head + i) % queue.length] = null;
            }
            head = 0;
            tail = 0;
            size = 0;
        }

        @SuppressWarnings("unchecked")
        T elementAt(int index) {
            return (T) queue[index];
        }
    }
}
