package Algorithms.스택;

import java.util.ArrayDeque;

public class Stack {
    public static void main(String[] args) {

    }
    /**
     * 1. 스택 직접구현
     * 2. Stack Collection 라이브러리
     * 3. Deque Collection 라이브러리 (push , pop)
     */

    class CustomStack{
        private int[] stack;
        private int top = 0;

        CustomStack(int capacity){
            stack = new int[Math.max(1, capacity)];
        }

        void push(int value){
            if(isFull()){
                int [] newStack = new int [stack.length * 2];
                System.arraycopy(stack, 0, newStack, 0, top);
                stack = newStack;
            }
            stack[top++] = value;

        }
        boolean isFull(){
            return top == stack.length;
        }

        int pop(){
            if(this.isEmpty()){
                throw new IllegalStateException();
            }
            return stack[--top];
        }
        boolean isEmpty(){
            return top == 0;
        }
        int peek(){
            if(this.isEmpty()){
                throw new IllegalStateException();
            }
            return stack[top - 1];
        }

        int size(){
            return top;
        }
        void clear() {
            top = 0;
        }
    }

    class GenericCustomStack<T>{
        private Object[] stack;
        private int top = 0;

        GenericCustomStack(int capacity){
            stack = new Object[Math.max(1, capacity)];
        }

        void push(T value){
            if(isFull()){
                Object [] newStack = new Object [stack.length * 2];
                System.arraycopy(stack, 0, newStack, 0, top);
                stack = newStack;
            }
            stack[top++] = value;

        }
        boolean isFull(){
            return top == stack.length;
        }

        T pop(){
            if(this.isEmpty()){
                throw new IllegalStateException();
            }
            T v = (T) stack[top - 1];
            stack[--top] = null;
            return v;
        }
        boolean isEmpty(){
            return top == 0;
        }
        T peek(){
            if(this.isEmpty()){
                throw new IllegalStateException();
            }
            return (T) stack[top - 1];
        }

        int size(){
            return top;
        }
        void clear() {
            for (int i = 0; i < top; i++) {
                stack[i] = null;
            }
            top = 0;
        }
    }
}
