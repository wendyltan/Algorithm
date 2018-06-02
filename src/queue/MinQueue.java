package queue;

import java.util.Stack;

public class MinQueue {
    /**
     * first use stack to simulate queue,
     * then make the stack a min stack
     */
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;
    private int min;

    public MinQueue(){
        dataStack = new Stack<>();
        minStack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x){
        Stack<Integer> tmp = new Stack<>();
        while (!dataStack.isEmpty()){
            tmp.push(dataStack.pop());
        }
        dataStack.add(x);
        min = Math.min(min,x);
        minStack.add(min);
        while(!tmp.isEmpty()){
            dataStack.push(tmp.pop());
        }

    }
    public void pop(){
        dataStack.pop();
        minStack.pop();
        min = minStack.isEmpty()? Integer.MAX_VALUE : minStack.peek();
    }

    public int peek(){
        return dataStack.peek();
    }

    public int getMin(){
        return min;
    }
}
