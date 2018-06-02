package stack;

import java.util.LinkedList;
import java.util.Queue;

public class QueueStack {
    /**
     * use queue to simulate stack
     */

    private Queue<Integer> queue;

    public QueueStack(){
        queue = new LinkedList<>();
    }

    public void push(int x){
        queue.add(x);
        int size = queue.size();
        while (size-- >1){
            //add the first deleted element
            queue.add(queue.poll());
        }
        //reverse the element in queue
    }
    public int pop(){
        //remove the first element in queue
        //that is,the top of the stack
        return queue.remove();
    }

    public int top(){
        return queue.peek();
    }

    public boolean empty(){
        return queue.isEmpty();
    }
}
