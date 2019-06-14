package LeetCode;

import java.util.HashMap;

/**
 * Implement a data structure supporting the following operations:
 * Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1.
 * Key is guaranteed to be a non-empty string.
 * Dec(Key) - If Key's value is 1, remove it from the data structure.
 * Otherwise decrements an existing key by 1. If the key does not exist,
 * this function does nothing. Key is guaranteed to be a non-empty string.
 * GetMaxKey() - Returns one of the keys with maximal value.
 * If no element exists, return an empty string "".
 * GetMinKey() - Returns one of the keys with minimal value.
 * If no element exists, return an empty string "".
 * Challenge: Perform all these in O(1) time complexity.
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-oone-data-structure
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AllOne {
    HashMap<String, Integer> dataMap;
    int MaxValue;
    int MaxCount;

    int MinValue;
    int MinCount;

    int freshValue;
    int dataSize;

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
        dataMap = new HashMap<>();
        freshValue = 0;
        dataSize = 0;
        MinValue = -1;
        MaxValue = -1;
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (dataMap.containsKey(key)) {
            int originalVal = dataMap.get(key);
            if (originalVal == 1) {
                freshValue--;
            }
            int newVal = originalVal + 1;
            if (newVal > MaxValue) {
                MaxValue = newVal;
                MaxCount = 1;
            }else if(newVal == MaxValue){
                MaxCount ++;
            }

            dataMap.put(key, newVal);
        } else {
            dataSize++;
            freshValue++;
            dataMap.put(key, 1);

            if(1 > MaxValue){
                MaxValue = 1;
                MaxCount = 1;
            }else if(1 == MaxValue) {
                MaxCount ++;
            }

            if(MinValue == -1){
                MinValue ==
            }
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if(!dataMap.containsKey(key)){
            return;
        }
        int originalVal = dataMap.get(key);
        if (originalVal == MaxValue){
            MaxCount --;
        }


        int newVal = originalVal - 1 ;
        if(newVal == 0){
            dataMap.remove(key);
            dataSize --;
        }


    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {

    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {

    }
    /**
     * Your AllOne object will be instantiated and called as such:
     * AllOne obj = new AllOne();
     * obj.inc(key);
     * obj.dec(key);
     * String param_3 = obj.getMaxKey();
     * String param_4 = obj.getMinKey();
     */
}
