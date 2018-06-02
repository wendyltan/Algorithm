package queue;

import java.util.Stack;

public class OneStackQueue {
    /**
     * using one stack to simulate queue
     */
    private Stack<Integer> st = new Stack<>();

    public void push(int x){
        //use temp stack here for saving elements
        Stack<Integer> temp = new Stack<>();
        while (!st.isEmpty()){
            temp.push(st.pop());
        }

        st.push(x);
        while(!temp.isEmpty()){
            st.push(temp.pop());
        }
    }

    public int pop(){
        //first in will be first out,so simply just pop.
        return st.pop();
    }

    public int peek(){
        return st.peek();
    }
    public boolean empty(){
        return st.isEmpty();
    }
}
