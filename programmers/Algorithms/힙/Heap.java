package Algorithms.힙;

public class Heap {
    class CustomMinHeap {
        int [] heap = new int [10001];
        int size;

        void add(int value) {
            if (size == heap.length) {
                resize();
            }

            heap[size] = value;
            siftUp(size);
            size++;
        }
        void resize() {
            int[] newHeap = new int[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, size);
            heap = newHeap;
        }
        void siftUp(int index) {
            while (index > 0) {
                int parent = (index - 1) / 2;

                if (heap[parent] <= heap[index]) {
                    break;
                }

                swap(heap, parent, index);
                index = parent;
            }
        }
        int peek(){
            if(size == 0){
                throw new IllegalStateException();
            }
            return heap[0];
        };

        int poll() {
            if (size == 0) {
                throw new IllegalStateException("Heap is empty");
            }

            int result = heap[0];
            heap[0] = heap[size - 1];
            size--;

            if (size > 0) {
                siftDown(0);
            }

            return result;
        }
        void siftDown(int index) {
            while (true) {
                int left = 2 * index + 1;
                int right = 2 * index + 2;
                int smallest = index;

                if (left < size && heap[left] < heap[smallest]) {
                    smallest = left;
                }

                if (right < size && heap[right] < heap[smallest]) {
                    smallest = right;
                }

                if (smallest == index) {
                    break;
                }

                swap(heap, index, smallest);
                index = smallest;
            }
        }
        boolean isEmpty(){
            return size == 0;
        };

        private void swap(int[] ary , int i, int j){
            int temp = ary[i];
            ary[i] = ary[j];
            ary[j] = temp;
        }
    }

    class CustomMaxHeap {
        int [] heap = new int[10001];
        int size;

        void add(int value) {
            if (size == heap.length) {
                resize();
            }

            heap[size] = value;
            siftUp(size);
            size++;
        }
        void resize() {
            int[] newHeap = new int[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, size);
            heap = newHeap;
        }
        int poll() {
            if (size == 0) {
                throw new IllegalStateException("Heap is empty");
            }

            int result = heap[0];
            heap[0] = heap[size - 1];
            size--;

            if (size > 0) {
                siftDown(0);
            }

            return result;
        }
        void siftDown(int index) {
            while (true) {
                int left = 2 * index + 1;
                int right = 2 * index + 2;
                int biggest = index;

                if (left < size && heap[left] > heap[biggest]) {
                    biggest = left;
                }

                if (right < size && heap[right] > heap[biggest]) {
                    biggest = right;
                }

                if (biggest == index) {
                    break;
                }

                swap(index, biggest);
                index = biggest;
            }
        }

        void siftUp(int index){
            while(index > 0){
                int parent = (index - 1) / 2;

                if (heap[parent] >= heap[index]) {
                    break;
                }

                swap(parent, index);
                index = parent;

            }
        }


        boolean isEmpty(){
            return size == 0;
        }

        private void swap(int i, int j){
            int value = heap[i];
            heap[i] = heap[j];
            heap[j] = value;
        }
    }
}
