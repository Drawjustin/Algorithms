package Algorithms.큐;

public class Deque {
    class CustomDeque<T>{
        Object [] array;
        int head;
        int tail;
        int size;

        CustomDeque(int capacity){
            int n = 16;
            while(capacity > n){
                n = n << 1;
            }
            array = new Object[n];
        }

        void offerFirst(T value) {
            if (isFull()) {
                grow();
            }
            head = (head - 1 + array.length) % array.length;
            array[head] = value;
            size++;
        }

        void offerLast(T value) {
            if (isFull()) {
                grow();
            }
            array[tail] = value;
            tail = (tail + 1) % array.length;
            size++;
        }

        T pollFirst() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            T value = elementAt(head);
            array[head] = null;
            head = (head + 1) % array.length;
            size--;
            return value;
        }

        T pollLast() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            tail = (tail - 1 + array.length) % array.length;
            T value = elementAt(tail);
            array[tail] = null;
            size--;
            return value;
        }

        T peekFirst() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            return elementAt(head);
        }

        T peekLast() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            int lastIndex = (tail - 1 + array.length) % array.length;
            return elementAt(lastIndex);
        }

        boolean isFull() {
            return size == array.length;
        }

        boolean isEmpty() {
            return size == 0;
        }

        int size() {
            return size;
        }

        void clear() {
            for (int i = 0; i < size; i++) {
                array[(head + i) % array.length] = null;
            }
            head = 0;
            tail = 0;
            size = 0;
        }

        void grow() {
            Object[] newArray = new Object[array.length * 2];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[(head + i) % array.length];
            }
            array = newArray;
            head = 0;
            tail = size;
        }

        @SuppressWarnings("unchecked")
        T elementAt(int index) {
            return (T) array[index];
        }
    }
}
