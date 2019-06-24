package LeetCode;

import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

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
    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("a");
        allOne.inc("a");
        String xa2 = allOne.getMaxKey();
        String ia2 = allOne.getMinKey();
        allOne.inc("b");
        String ib1 = allOne.getMaxKey();
        allOne.inc("b");
        allOne.dec("a");
        String xb2 = allOne.getMaxKey();
        String ia1 = allOne.getMinKey();
    }


    private Comparator<Map.Entry<String, Integer>> dataModelComparator = new Comparator<Map.Entry<String, Integer>>() {
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            if (o1.getValue() < o2.getValue())
                return 1;
            if (o1.getValue() > o2.getValue())
                return -1;
            return 0;
        }
    };

    private TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(dataModelComparator);

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (treeMap.containsKey(key)) {
            int newValue = 1 + treeMap.get(key);
            treeMap.remove(key);
            treeMap.put(key, newValue);
        } else {
            treeMap.put(key, 1);
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if (treeMap.containsKey(key)) {
            int newValue = treeMap.get(key) - 1;
            if (newValue <= 0) {
                treeMap.remove(key);
            } else {
                treeMap.remove(key);
                treeMap.put(key, newValue);
            }
        }
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        String firstKey = treeMap.firstKey();
        if(StringUtils.isNotEmpty(firstKey))
            return firstKey;
        else
            return "";
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        String lastKey = treeMap.lastKey();
        if(StringUtils.isNotEmpty(lastKey))
            return lastKey;
        else
            return "";
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
