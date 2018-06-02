package stack;

import java.util.Stack;

public class MinStack {
    /**
     * use two stack to implements a min stack(be able to know the
     * minimum value in stack)
     * one stack for data and the other for min value
     */
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;
    private int min;

    public MinStack(){
        dataStack = new Stack<>();
        minStack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x){
        dataStack.add(x);
        min = Math.min(min,x);
        minStack.add(min);
    }

    public void pop(){
        dataStack.pop();
        minStack.pop();
        min = minStack.isEmpty()? Integer.MAX_VALUE : minStack.peek();
    }

    public int top(){
        return dataStack.peek();
    }

    public int getMin() {
        return min;
    }
}
