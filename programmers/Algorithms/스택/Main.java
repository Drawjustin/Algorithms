package Algorithms.스택;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }
    /**
     * 1. 스택 직접구현
     * 2. Stack Collection 라이브러리
     * 3. Deque Collection 라이브러리 (push , pop)
     */

    class CustomStack{
        int[] stack;
        int top = -1;

        CustomStack(int capacity){
            stack = new int[Math.max(1, capacity)];
        }

        void push(int value){
            if(isFull()){
                int [] newStack = new int [stack.length * 2];
                System.arraycopy(stack, 0, newStack, 0, top + 1);
                stack = newStack;
            }
            stack[++top] = value;

        }
        boolean isFull(){
            return top  == stack.length - 1;
        }

        int pop(){
            if(this.isEmpty()){
                throw new IllegalStateException();
            }
            return stack[top--];
        }
        boolean isEmpty(){
            return top == -1;
        }
        int peek(){
            if(this.isEmpty()){
                throw new IllegalStateException();
            }
            return stack[top];
        }

        int size(){
            return top + 1;
        }
        void clear() {
            top = -1;
        }
    }
}
