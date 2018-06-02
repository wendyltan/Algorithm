package hashtable_set_map;

import java.util.HashMap;
import java.util.Map;

public class TestHash {
    public static void main(String[] args){
        int[] a = {2,3,4,6};
        for(int i :twoSum(a,5)){
            System.out.print(i+" ");
        }
        System.out.println();

        int[] b = {2,3,4,6,2,2,3};
        System.out.println(findLHS(b));
    }

    /**
     *
     * @param nums
     * @param target
     * @return the index of the two number in nums which can add up to target
     */

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //found two numbers add up to the target!
            if (map.containsKey(target - nums[i])) return new int[] { map.get(target - nums[i]), i };
            else map.put(nums[i], i);
        }
        return null;
    }

    /**
     * Longest harmonious sequence(the biggest num minus smallest num is one,called harmonious)
     * Input: [1,3,2,2,5,2,3,7]
     * Output: 5(length)
     * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
     * @param nums
     * @return
     */
    public static int findLHS(int[] nums) {
        Map<Long, Integer> map = new HashMap<>();
        for (long num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int result = 0;
        for (long key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                result = Math.max(result, map.get(key + 1) + map.get(key));
            }
        }
        return result;
    }
}
