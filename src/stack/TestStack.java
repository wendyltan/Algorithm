package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TestStack {
    public static void main(String[] args){
        QueueStack queueStack = new QueueStack();
        queueStack.push(1);
        queueStack.push(12);
        //1 12
        queueStack.pop();
        //1
        queueStack.push(11);
        //1 11
        System.out.println(queueStack.top());

        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(1);
        //2 1
        System.out.println(minStack.getMin());
        minStack.pop();
        //2
        System.out.println(minStack.getMin());

        System.out.println(isValid("()"));
        int[] a = {73, 74, 75, 71, 69, 72, 76, 73};
        for (int i:dailyTemperatures(a)){
            System.out.print(i+" ");
        }
        System.out.println();

        int[] b = {4,1,2};
        int[] c = {1,3,4,2};

        for (int i:nextGreaterElement(b,c)){
            System.out.print(i+" ");
        }

    }

    /**
     * "()[]{}"
     * Use stack to match the brackets
     * Output : true
     */
    public static boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for (char c: s.toCharArray()){
            if (c == '('||c=='{'||c=='[') stack.push(c);
            else{
                if (stack.isEmpty()) return false;//didn't match
                char cStack = stack.pop();
                boolean b1 = c == ')' && cStack != '(';
                boolean b2 = c == ']' && cStack != '[';
                boolean b3 = c == '}' && cStack != '{';
                if (b1||b2||b3) return false;

            }
        }
        return stack.isEmpty();
    }

    /**
     * Next greater element distance(in one array)
     * Input: [73, 74, 75, 71, 69, 72, 76, 73]
     * Output: [1, 1, 4, 2, 1, 1, 0, 0]
     */

    public static int[] dailyTemperatures(int[] temperatures){
        int n = temperatures.length;
        //the return array
        int[] ret = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<n;i++){
            while (!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]){
                int idx = stack.pop();
                ret[idx] = i-idx;
            }
            //use stack to save index
            stack.add(i);
        }

        return ret;
    }

    /**
     * Next greater element
     * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * Output: [-1,3,-1]
     */
    public static int[] nextGreaterElement(int[] nums1,int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                //for example, (1,3) means the element 1's next greater is 3
                map.put(stack.pop(), num);
            }
            stack.add(num);
        }
        int[] ret = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) {
                ret[i] = map.get(nums1[i]);
            } else ret[i] = -1;
        }
        return ret;
    }
}
