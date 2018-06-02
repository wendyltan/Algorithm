package queue;

public class TestQueue {
    public static void main(String[] args){
        OneStackQueue oneStackQueue = new OneStackQueue();
        oneStackQueue.push(1);
        oneStackQueue.push(2);
        //2 1
        oneStackQueue.push(3);
        //3 2 1
        oneStackQueue.pop();
        //3 2
        //now 2 is at the head of the queue.(top of the stack)
        System.out.println(oneStackQueue.peek());

        TwoStackQueue twoStackQueue = new TwoStackQueue();
        twoStackQueue.push(2);
        twoStackQueue.push(4);
        twoStackQueue.push(5);
        //2 4 5  |
        twoStackQueue.pop();
        //       | 4 5
        //4 5    |
        twoStackQueue.push(7);
        //4 5 7  |

        System.out.println(twoStackQueue.peek());

        MinQueue minQueue = new MinQueue();
        minQueue.push(1);
        minQueue.push(3);
        //3 1
        System.out.println(minQueue.getMin());
        //1
        minQueue.pop();
        //11 3
        minQueue.push(11);
        System.out.println(minQueue.peek());
    }

}
